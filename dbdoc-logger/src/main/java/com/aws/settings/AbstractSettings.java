package com.aws.settings;

import com.aws.Logger;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Base class for settings
 */
abstract class AbstractSettings implements Settings {
    private final List<ChangeListener> listeners = new LinkedList<>();
    private final Map<String, String> settingsMap = new TreeMap<>();
    private final String prefix;
    protected Map<String, Object> defaultValues;

    protected AbstractSettings(String name) {
        prefix = Objects.requireNonNull(name);
    }

    /**
     * Set value object for key
     *
     * @param key   name of key for saving object
     * @param value saving value
     */
    public void set(String key, Object value) {
        String newValue = Objects.toString(value, null);
        String oldValue = settingsMap.put(key, newValue);
        if (!Objects.equals(oldValue, newValue)) {
            Logger.GENERAL.writeInfo(
                    "%s.%s changed \n    from: %s\n      to: %s", prefix, key, oldValue, newValue);
            onSettingChanged(key, oldValue, newValue);
        }
    }

    /**
     * Changes the setting value without invoking change listeners.
     *
     * @param key   - setting key
     * @param value - new value for specified setting.
     */
    protected void setValueInternal(String key, Object value) {
        String newValue = Objects.toString(value, null);
        String oldValue = settingsMap.put(key, newValue);
    }

    // region listeners

    private void onSettingChanged(String key, String oldValue, String newValue) {
        for (ChangeListener listener : listeners) {
            listener.onSettingChanged(key, oldValue, newValue);
        }
    }

    @Override
    public void addListener(ChangeListener listener) {
        listeners.add(Objects.requireNonNull(listener));
    }

    @Override
    public void removeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    // endregion listeners

    /**
     * Method returns value by key
     *
     * @param key key of object
     * @return value by key or null if no value exist for this key
     */
    public String get(String key) {
        return settingsMap.get(key);
    }

    public Object getObject(String key, Class<?> valueObject) {
        String valueString = get(key);
        if (valueString == null) {
            return null;
        }
        return new GsonBuilder().create().fromJson(valueString, valueObject);
    }

    @Override
    public <T> void setObject(String key, T t) {
        String newValue = getObjectStringValue(t);
        String oldValue = settingsMap.put(key, newValue);
        if (!Objects.equals(oldValue, newValue)) {
            Logger.GENERAL.writeInfo(
                    "%s.%s changed \n    from: %s\n      to: %s", prefix, key, oldValue, newValue);
            onSettingChanged(key, oldValue, newValue);
        }
    }

    protected static <T> String getObjectStringValue(T t) {
        return new GsonBuilder().create().toJson(t);
    }

    public Map<String, String> getSettingsMap() {
        return settingsMap;
    }

    protected abstract File getSettingsFile();

    /**
     * Method for saving settings map to specified XML file.
     */
    public void save() throws Exception {
        try {
            File savedSettingsFile = getSettingsFile();
            if (savedSettingsFile == null) {
                Logger.GENERAL.writeError("File path is not set for %s. Saving cancelled.");
                return;
            }

            // ensure parent directory exists
            File savedSettingsDirectory = savedSettingsFile.getParentFile();
            if (!savedSettingsDirectory.exists() && !savedSettingsDirectory.mkdirs())
                throw new IllegalStateException("Failed to create directory " + savedSettingsDirectory);

            // create the document
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement(prefix);
            document.appendChild(rootElement);

            // put settings to the document
            for (String key : settingsMap.keySet()) {
                Element itemElement = document.createElement("setting");
                Attr k = document.createAttribute("key");
                k.setValue(key);
                itemElement.setAttributeNode(k);

                Attr v = document.createAttribute("value");
                v.setValue(settingsMap.get(key));
                itemElement.setAttributeNode(v);
                rootElement.appendChild(itemElement);
            }

            // save the document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            Logger.GENERAL.writeDebug("doc=" + document);
            DOMSource domSource = new DOMSource(document);
            transform(transformer, domSource, savedSettingsFile);
            Logger.GENERAL.writeInfo("%s saved.", prefix);
        } catch (Exception e) {
            Logger.GENERAL.writeError(e, "Failed to save %s. Saving cancelled.", prefix);
            throw e;
        }
    }

    private void transform(Transformer transformer, DOMSource domSource, File savedSettingsFile) throws TransformerException, IOException {
        try (OutputStream output = new FileOutputStream(savedSettingsFile)) {
            StreamResult streamResult = new StreamResult(output);
            transformer.transform(domSource, streamResult);
        }
    }

    /**
     * Method reads settings from specified XML file to settings map
     */
    public void load(boolean saveDefaultIfNotExist) {
        // Reset to default values to keep settings in consistent state in case of loading errors.
        // Also applies default values for new settings missing in stored file.
        resetToDefaultValues();

        try {
            File savedSettingsFile = getSettingsFile();
            if (savedSettingsFile == null) {
                Logger.GENERAL.writeError("File path is not set for %s. Default settings will be applied.");
                return;
            }

            if (!getSettingsFile().exists()) {
                if (saveDefaultIfNotExist)
                    save();

                //TODO check debug messages flow
                return /* default values */;
            }

            // Read document from file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(savedSettingsFile);
            document.getDocumentElement().normalize();

            // Set preferences form document
            NodeList nodeList = document.getElementsByTagName(
                    document.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                Node node = nodeList.item(tmp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    settingsMap.put(el.getAttribute("key"), el.getAttribute("value"));
                }
            }

        } catch (Exception e) {
            Logger.GENERAL.writeError(e, "Failed to load %s. Default settings will be applied.", prefix);
            resetToDefaultValues();
        }

    }

    public void dumpSettings() {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(":\n");
        for (Map.Entry<String, String> entry : settingsMap.entrySet()) {
            builder.append("    ").append(entry.getKey()).append('=').append(entry.getValue()).append('\n');
        }
        Logger.GENERAL.writeInfo(builder.toString());
    }

    public void resetToDefaultValues() {
        settingsMap.clear();
        for (Map.Entry<String, Object> entry : getDefaultValues().entrySet()) {
            setValueInternal(entry.getKey(), entry.getValue());
        }
    }

    protected Map<String, Object> getDefaultValues() {
        if (defaultValues == null) {
            initDefaultValues();
        }
        return defaultValues;
    }

    protected abstract void initDefaultValues();

    @Override
    public String getDefaultValue(String key) {
        return Objects.toString(getDefaultValues().get(key), null);
    }

    public void setAndResetDefault(String key, Object value, boolean isNeedReset) {
        if (isNeedReset) {
            set(key, value);
        }
        getDefaultValues().put(key, value);
    }
}
