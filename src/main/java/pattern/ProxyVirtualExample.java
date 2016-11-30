package pattern;

//  https://www.youtube.com/watch?v=ooPPd_LIkzQ
public class ProxyVirtualExample {
    public static void main(String[] args) {
        final Image image1 = new ProxyImage("HiResolution_100MB_Dog Photo");
        final Image image2 = new ProxyImage("HiResolution_100MB_Lion Photo");

        System.out.println("Image1[" + image1 + "]  calling displayImage first time");
        image1.displayImage();
        System.out.println("Image1[" + image1 + "]  calling displayImage second time");
        image1.displayImage();
        System.out.println("Image1[" + image1 + "]  calling displayImage third time");
        image1.displayImage();

        System.out.println("#####################################################\n");

        System.out.println("Image2[" + image2 + "]  calling displayImage first time");
        image1.displayImage();
        System.out.println("Image2[" + image2 + "]  calling displayImage second time");
        image1.displayImage();
    }
}

interface Image {
    public void displayImage();
}

class RealImage implements Image {
    private String fileName = null;

    public RealImage(final String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("      Loading      " + fileName);
    }

    @Override
    public void displayImage() {
        System.out.println("      Displaying      " + fileName + "\n");
    }
}

class ProxyImage implements Image {
    private String fileName = null;
    private RealImage image = null;

    public ProxyImage(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void displayImage() {
        if (image == null) image = new RealImage(fileName);
        System.out.println("      ProxyImage  ");
    }
}