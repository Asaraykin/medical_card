/**
 * $RCSfile: Signature.java,v $
 * version $Revision: 1.13 $
 * created 22.06.2007 14:13:15 by kunina
 * last modified $Date: 2009/09/25 13:12:13 $ by $Author: kunina $
 * (C) ООО Крипто-Про 2004-2007.
 *
 * Программный код, содержащийся в этом файле, предназначен
 * для целей обучения. Может быть скопирован или модифицирован 
 * при условии сохранения абзацев с указанием авторства и прав.
 *
 * Данный код не может быть непосредственно использован
 * для защиты информации. Компания Крипто-Про не несет никакой
 * ответственности за функционирование этого кода.
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
 * Формирование электронной цифровой подписи в соответствии с алгоритмом ГОСТ Р
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
 * </DD> <DL> <DT><b> -alias </b>  <DD>уникальное имя ключа</DD> <DT><b>
 * -keypass </b> <DD>пароль на записываемый ключ <DD>(по умолчанию null)<br>
 * <DT><b> -storetype </b> <DD>имя ключевого носителя HDImageStore (жесткий
 * диск), FloppyStore (дискета), OCFStore или J6CFStore (карточки) <DD>(по
 * умолчанию HDImageStore)</DD> <DT><b>-storepath </b> <DD>путь к хранилищу
 * доверенных сертификатов <DD>(по умолчанию null)</DD> <DT><b> -storepass </b>
 * <DD>пароль на хранилище доверенных сертификатов <DD>(по умолчанию null)</DD>
 * <DT><b> -signpath </b> <DD>путь к файлу подписи </DD> <DT><b> -filepath</b>
 * <DD>путь к подписываемому файлу</DD></DT> </DL>
 *
 * @param args пары аргументов командной строки
 */
public static void main(String[] args) {
    if (ComLine.getFunc(ComLine.hllp, args)) log.info(ComLine.SignGenHelpHD);
    else
        try {
            //Заполнение таблицы свойств значениями параметров командной строки
            //или значениями по умолчанию при отсутствии первых
            final Properties ArgList = new Properties();
            //уникальное имя ключа
            ArgList.setProperty(ComLine.ALIAS,
                    ComLine.getValue(ComLine.ALIAS, args, null));
            //пароль на ключ
            ArgList.setProperty(ComLine.keypass,
                    ComLine.getValue(ComLine.keypass, args, "null"));
            //тип ключевого носителя
            ArgList.setProperty(ComLine.storetype,
                    ComLine.getValue(ComLine.storetype, args,
                            ComLine.HDImageStore));
            //путь к хранилищу доверенных сертификатов
            ArgList.setProperty(ComLine.storepath,
                    ComLine.getValue(ComLine.storepath, args, "null"));
            //пароль на хранилище доверенных сертификатов
            ArgList.setProperty(ComLine.storepass,
                    ComLine.getValue(ComLine.storepass, args, "null"));
            //алгоритм формирования ЭЦП
            ArgList.setProperty(ComLine.SignAlgoritm, "GOST3411withGOST3410EL");
            //путь к файлу подписи
            ArgList.setProperty(ComLine.signpath,
                    ComLine.getValue(ComLine.signpath, args, null));
            //путь к подписываемому файлу
            ArgList.setProperty(ComLine.filepath,
                    ComLine.getValue(ComLine.filepath, args, null));

            //Проверка типа хранилища.
            //При неверном вводе присваивается значение по умолчанию HDImageStore.
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
            //Генерирование и запись ЭЦП
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
 * @param alias уникальное имя ключа
 * @param keypass пароль на ключ
 * @param keystoreName тип ключевого носителя
 * @param signAlgorithm алгоритм ЭЦП
 * @param filePath путь к подписываемому файлу
 * @param keystorePass пароль на хранилище доверенных сертификатов
 * @param keystorePath путь к хранилищу доверенных сертификатов
 * @param signPath путь к файлу подписи
 * @throws Exception ...
 */
private static void sign(String alias, String keypass,
                         String keystoreName, String signAlgorithm,
                         String filePath,
                         String keystorePass, String keystorePath,
                         String signPath)
        throws Exception {
    //загрузка ключа из хранилища HDImageStore
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

    //чтение текста
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

    //генерирование ЭЦП
    final java.security.Signature sig;
    sig = java.security.Signature.getInstance(signAlgorithm);
    sig.initSign(privateKey);
    sig.update(text);

    //запись подписи в файл
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

