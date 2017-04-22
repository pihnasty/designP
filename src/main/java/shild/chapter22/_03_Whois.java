package shild.chapter22;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class _03_Whois {
    public static void main(String args[]) throws Exception {
        int c;

        // Create a socket connected to internic.net, port 43.
        Socket s = new Socket("whois.internic.net", 43);

        // Obtain input and output streams.
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        // Construct a request string.

        args[0] = "mail.ru";
        String str = (args.length == 0 ? "MHProfessional.com" : args[0]) + "\n";
        // Convert to bytes.
        byte buf[] = str.getBytes();

        // Send request.
        out.write(buf);

        // Read and display response.
        while ((c = in.read()) != -1) {
            System.out.print((char) c);

        }
        s.close();
    }
}


//    Domain names in the .com and .net domains can now be registered
//        with many different competing registrars. Go to http://www.internic.net
//        for detailed information.
//
//        Aborting search 50 records found .....
//        MAIL.RU
//        MAIL.RUANGVIRTUAL.COM.DIRECTIDELETEDDOMAIN.COM
//        MAIL.RUBICONENTERPRISES.NET
//        MAIL.RUBIO-ET-ASSOCIES.COM
//        MAIL.RUBON925.COM
//        MAIL.RUCCINET.COM
//        MAIL.RUCCIUS.COM
//        MAIL.RUDERPROJECTS.COM
//        MAIL.RUDOLF-NET.CZ
//        MAIL.RUDYDEGGER.COM
//        MAIL.RUDYNET.PL
//        MAIL.RUENKANG.COM
//        MAIL.RUFEGER.DE
//        MAIL.RUFFLIFEPETPHOTO.COM
//        MAIL.RUGBYFEVER.COM
//        MAIL.RUGELESDURANARQ.COM
//        MAIL.RUICUNHA.DYNDNS.ORG
//        MAIL.RUIGROK.NL
//        MAIL.RUIYUESERVICE.COM
//        MAIL.RUIZLOPEZASESORES.COM
//        MAIL.RULERADIO123.BIZ
//        MAIL.RULIETTA.COM
//        MAIL.RUM.HU
//        MAIL.RUMAHILMU.NET
//        MAIL.RUMETCO.NET
//        MAIL.RUN4EASYTEACHING.COM
//        MAIL.RUNJINSY.COM
//        MAIL.RUNMAD.COM
//        MAIL.RUNNER-UP.NET
//        MAIL.RUNNEROWL.COM
//        MAIL.RUNNINGHOST.COM
//        MAIL.RUNSA.COM.MX
//        MAIL.RUNSYS.DE
//        MAIL.RUPEREZYGARCIA.COM
//        MAIL.RUPLACK.COM
//        MAIL.RUPPIN.AC.IL
//        MAIL.RURA.NET.PL
//        MAIL.RURALWATERIMPACT.COM
//        MAIL.RUS-FOND.RU
//        MAIL.RUSCO911.COM
//        MAIL.RUSHONIT.COM.HOLLOW1.COM
//        MAIL.RUSIEM.COM
//        MAIL.RUSINFOCOM.RU
//        MAIL.RUSKHLEB.RU
//        MAIL.RUSNETHOSTING.COM
//        MAIL.RUSO.CA
//        MAIL.RUTA.RU
//        MAIL.RUTHIESCANDLEKITCHEN.COM
//        MAIL.RUTHS-CHRIS.COM
//        MAIL.RUTIL.NET
//
//        To single out one record, look it up with "xxx", where xxx is one of the
//        records displayed above. If the records are the same, look them up
//        with "=xxx" to receive a full display for each record.
//
//        >>> Last update of whois database: Mon, 17 Apr 2017 09:21:41 GMT <<<
//
//        For more information on Whois status codes, please visit https://icann.org/epp
//
//        NOTICE: The expiration date displayed in this record is the date the
//        registrar's sponsorship of the domain name registration in the registry is
//        currently set to expire. This date does not necessarily reflect the expiration
//        date of the domain name registrant's agreement with the sponsoring
//        registrar.  Users may consult the sponsoring registrar's Whois database to
//        view the registrar's reported date of expiration for this registration.
//
//        TERMS OF USE: You are not authorized to access or query our Whois
//        database through the use of electronic processes that are high-volume and
//        automated except as reasonably necessary to register domain names or
//        modify existing registrations; the Data in VeriSign Global Registry
//        Services' ("VeriSign") Whois database is provided by VeriSign for
//        information purposes only, and to assist persons in obtaining information
//        about or related to a domain name registration record. VeriSign does not
//        guarantee its accuracy. By submitting a Whois query, you agree to abide
//        by the following terms of use: You agree that you may use this Data only
//        for lawful purposes and that under no circumstances will you use this Data
//        to: (1) allow, enable, or otherwise support the transmission of mass
//        unsolicited, commercial advertising or solicitations via e-mail, telephone,
//        or facsimile; or (2) enable high volume, automated, electronic processes
//        that apply to VeriSign (or its computer systems). The compilation,
//        repackaging, dissemination or other use of this Data is expressly
//        prohibited without the prior written consent of VeriSign. You agree not to
//        use electronic processes that are automated and high-volume to access or
//        query the Whois database except as reasonably necessary to register
//        domain names or modify existing registrations. VeriSign reserves the right
//        to restrict your access to the Whois database in its sole discretion to ensure
//        operational stability.  VeriSign may restrict or terminate your access to the
//        Whois database for failure to abide by these terms of use. VeriSign
//        reserves the right to modify these terms at any time.
//
//        The Registry database contains ONLY .COM, .NET, .EDU domains and
//        Registrars.
//
//        Process finished with exit code 0
