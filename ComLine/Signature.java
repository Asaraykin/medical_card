/**
 * $RCSfile: Signature.java,v $
 * version $Revision: 1.13 $
 * created 22.06.2007 14:13:15 by kunina
 * last modified $Date: 2009/09/25 13:12:13 $ by $Author: kunina $
 * (C) ��� ������-��� 2004-2007.
 *
 * ����������� ���, ������������ � ���� �����, ������������
 * ��� ����� ��������. ����� ���� ���������� ��� ������������� 
 * ��� ������� ���������� ������� � ��������� ��������� � ����.
 *
 * ������ ��� �� ����� ���� ��������������� �����������
 * ��� ������ ����������. �������� ������-��� �� ����� �������
 * ��������������� �� ���������������� ����� ����.
 */
package ComLine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * ������������ ����������� �������� ������� � ������������ � ���������� ���� �
 * 34.10-2001
 *
 * @author Copyright 2004-2007 Crypto-Pro. All rights reserved.
 * @.Version
 */
public class Signature {
/**
 * logger
 */
private static Logger log = Logger.getLogger("LOGGER");

/**
 * forbidden.
 */
private Signature() {
}

/**
 * Signature -alias name_of_key [-storetype HDImageStore] [-storepath null]
 * [-storepass null] [-keypass password] -signpath C:/*.* -filepath C:/*.*
 * <p/>
 * </DD> <DL> <DT><b> -alias </b>  <DD>���������� ��� �����</DD> <DT><b>
 * -keypass </b> <DD>������ �� ������������ ���� <DD>(�� ��������� null)<br>
 * <DT><b> -storetype </b> <DD>��� ��������� �������� HDImageStore (�������
 * ����), FloppyStore (�������), OCFStore ��� J6CFStore (��������) <DD>(��
 * ��������� HDImageStore)</DD> <DT><b>-storepath </b> <DD>���� � ���������
 * ���������� ������������ <DD>(�� ��������� null)</DD> <DT><b> -storepass </b>
 * <DD>������ �� ��������� ���������� ������������ <DD>(�� ��������� null)</DD>
 * <DT><b> -signpath </b> <DD>���� � ����� ������� </DD> <DT><b> -filepath</b>
 * <DD>���� � �������������� �����</DD></DT> </DL>
 *
 * @param args ���� ���������� ��������� ������
 */
public static void main(String[] args) {
    if (ComLine.getFunc(ComLine.hllp, args)) log.info(ComLine.SignGenHelpHD);
    else
        try {
            //���������� ������� ������� ���������� ���������� ��������� ������
            //��� ���������� �� ��������� ��� ���������� ������
            final Properties ArgList = new Properties();
            //���������� ��� �����
            ArgList.setProperty(ComLine.ALIAS,
                    ComLine.getValue(ComLine.ALIAS, args, null));
            //������ �� ����
            ArgList.setProperty(ComLine.keypass,
                    ComLine.getValue(ComLine.keypass, args, "null"));
            //��� ��������� ��������
            ArgList.setProperty(ComLine.storetype,
                    ComLine.getValue(ComLine.storetype, args,
                            ComLine.HDImageStore));
            //���� � ��������� ���������� ������������
            ArgList.setProperty(ComLine.storepath,
                    ComLine.getValue(ComLine.storepath, args, "null"));
            //������ �� ��������� ���������� ������������
            ArgList.setProperty(ComLine.storepass,
                    ComLine.getValue(ComLine.storepass, args, "null"));
            //�������� ������������ ���
            ArgList.setProperty(ComLine.SignAlgoritm, "GOST3411withGOST3410EL");
            //���� � ����� �������
            ArgList.setProperty(ComLine.signpath,
                    ComLine.getValue(ComLine.signpath, args, null));
            //���� � �������������� �����
            ArgList.setProperty(ComLine.filepath,
                    ComLine.getValue(ComLine.filepath, args, null));

            //�������� ���� ���������.
            //��� �������� ����� ������������� �������� �� ��������� HDImageStore.
            final String ks;
            ks = ArgList.getProperty(ComLine.storetype);
            if (ks.equalsIgnoreCase(ComLine.HDImageStore)) {
            } else if (ks.equalsIgnoreCase(ComLine.FloppyStore)) {
            } else if (ks.equalsIgnoreCase(ComLine.OCFStore)) {
            } else if (ks.equalsIgnoreCase(ComLine.J6CFStore)) {
            } else {
                ArgList.setProperty(ComLine.storetype, ComLine.HDImageStore);
                Logger.getLogger("LOGGER")
                        .info("Incorrect keystore type: " + ks +
                                ". Value by default is appropriated: " + ComLine
                                .HDImageStore);
            }
            //������������� � ������ ���
            sign(
                    ArgList.getProperty(ComLine.ALIAS),
                    ArgList.getProperty(ComLine.keypass),
                    ArgList.getProperty(ComLine.storetype),
                    ArgList.getProperty(ComLine.SignAlgoritm),
                    ArgList.getProperty(ComLine.filepath),
                    ArgList.getProperty(ComLine.storepass),
                    ArgList.getProperty(ComLine.storepath),
                    ArgList.getProperty(ComLine.signpath));

        } catch (NullPointerException e) {
            //System.out.println(e.toString());
            Logger.getLogger("LOGGER").info(ComLine.SignGenHelpHD);
        } catch (Exception e1) {
            final String sss = "java.lang.Exception:";
            log.info("\n" +
                    e1.toString()
                            .substring(sss.length(), e1.toString().length()) +
                    "\n" + ComLine.SignGenHelpHD);
        }
}

/**
 * @param alias ���������� ��� �����
 * @param keypass ������ �� ����
 * @param keystoreName ��� ��������� ��������
 * @param signAlgorithm �������� ���
 * @param filePath ���� � �������������� �����
 * @param keystorePass ������ �� ��������� ���������� ������������
 * @param keystorePath ���� � ��������� ���������� ������������
 * @param signPath ���� � ����� �������
 * @throws Exception ...
 */
private static void sign(String alias, String keypass,
                         String keystoreName, String signAlgorithm,
                         String filePath,
                         String keystorePass, String keystorePath,
                         String signPath)
        throws Exception {
    //�������� ����� �� ��������� HDImageStore
    final KeyStore ks = KeyStore.getInstance(keystoreName);
    char[] KeyStorePass = null;
    if (!"null".equalsIgnoreCase(keystorePass)) {
        KeyStorePass = keystorePass.toCharArray();
    }
    InputStream is = null;
    if (!"null".equalsIgnoreCase(keystorePath)) {
        is = new FileInputStream(keystorePath);
    }
    ks.load(is, KeyStorePass);
    //ks.load(null, null);
    final PrivateKey privateKey;
    char[] Keypass = null;
    if (!"null".equalsIgnoreCase(keypass)) {
        Keypass = keypass.toCharArray();
    }
    privateKey = (PrivateKey) ks.getKey(alias, Keypass);
    if (privateKey == null)
        throw new Exception("Key named \"" + alias + "\" not found");
    //log.info("Loading of a private key is completed");

    //������ ������
    final byte[] text;
    FileInputStream fis = null;
    try {
        fis = new FileInputStream(filePath);
        text = new byte[fis.available()];
        int len;
        int tot = 0;
        do {
            len = fis.read(text, tot, text.length - tot);
            tot += len;
        } while (len > 0);
    } finally {
        if (fis != null) fis.close();
    }
    //log.info("Loading of a text is completed");

    //������������� ���
    final java.security.Signature sig;
    sig = java.security.Signature.getInstance(signAlgorithm);
    sig.initSign(privateKey);
    sig.update(text);

    //������ ������� � ����
    final byte[] signature;
    signature = sig.sign();
    FileOutputStream fos = null;
    try {
        fos = new FileOutputStream(signPath);
        fos.write(signature);
    } finally {
        if (fos != null) fos.close();
    }
    log.info("Generation and recording of the signature is completed");

}


}

