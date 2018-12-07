/**
 * $RCSfile: ComLine.java,v $
 * version $Revision: 1.22 $
 * created 20.06.2007 17:15:15 by kunina
 * last modified $Date: 2011/08/12 12:31:26 $ by $Author: borodin $
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

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Командная строка
 *
 * @kunina Copyright 2004-2007 Crypto-Pro. All rights reserved.
 * @.Version
 */
public class ComLine {
/**
 * logger
 */
private static Logger log = Logger.getLogger("LOGGER");

/**
 * forbidden
 */
private ComLine() {

}


/**
 * Разбор командной строки
 *
 * @param args параметры командной строки
 */
public static void main(String[] args) {
    try {
        final Class aClass = Class.forName("ComLine." + args[0]);
        final Method[] methods = aClass.getMethods();
        Method main = null;
        for (int i = 0; i < methods.length; i++) {
            if ("main".equalsIgnoreCase(methods[i].getName()))
                main = methods[i];
        }
        final Object[] Args = {args};
        if (main != null) {
            main.invoke(null, Args);
        }

    } catch (ArrayIndexOutOfBoundsException e) {
        log.info(HELP);
    } catch (ClassNotFoundException e) {
        log.info("Name of function is case sensitive\n" + HELP);
    } catch (NoClassDefFoundError e) {
        log.info("Name of function is case sensitive\n" + HELP);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * @param com параметр
 * @param arg аргументы командной строки (пары парамтр значение параметра)
 * @param parDef значение параметра по умолчанию
 * @return значение параметра
 */
public static String getValue(String com, String[] arg, String parDef) {
    String par = null;
    int i;
    for (i = 0; i < arg.length; i++)
        if (arg[i].equalsIgnoreCase(com) &&
                !"-".equals(arg[i + 1].substring(0, 1))) {
            par = arg[i + 1];
        }
    if (par == null) par = parDef;

    return par;
}

/**
 * @param com параметр
 * @param arg аргументы командной строки (пары парамтр значение параметра)
 * @return присутствует или нет
 */
public static boolean getFunc(String com, String[] arg) {
    int i;
    boolean par = false;
    for (i = 0; i < arg.length; i++)
        if (arg[i].equalsIgnoreCase(com)) {
            par = true;
        }
    return par;
}

//----------------------------------------------------------------------------//
/**
 * Аргументы командной строки
 */
//работа с ключами и сетификатами
public static final String HDImageStore = "HDImageStore";
public static final String FloppyStore = "FloppyStore";
public static final String OCFStore = "OCFStore";
public static final String J6CFStore = "J6CFStore";
public static final String NO_STORE = "NONE";
public static final String ALIAS = "-alias";
public static final String storetype = "-storetype";
public static final String storepath = "-storepath";
public static final String storepass = "-storepass";
public static final String keypass = "-keypass";
public static final String dname = "-dname";
public static final String SignAlgoritm = "SignAlgoritm";
public static final String signpath = "-signpath";
public static final String filepath = "-filepath";
public static final String alg = "-alg";
public static final String KeyPairAlgoritm = "KeyPairAlgoritm";
public static final String nameEx = "CN=autor,OU=Security,O=CryptoPro,C=RU";
public static final String reqCertpath = "-reqCertpath";
public static final String http = "-http";
public static final String certpath = "-certpath";
public static final String certs = "-certs";
public static final String isServer = "-isServer";
public static final String encoding = "-encoding";
//пуск клиента/сервера
public static final String PORT = "-port";
public static final String SERVER = "-server";
public static final String keyStoreType = "-keyStoreType";
public static final String trustStoreType = "-trustStoreType";
public static final String trustStorePath = "-trustStorePath";
public static final String trustStorePassword = "-trustStorePassword";
public static final String keyStorePassword = "-keyStorePassword";
public static final String fileget = "-fileget";
public static final String fileout = "-fileout";
public static final String auth = "-auth";
public static final String servDir = "-servDir";
//функции
public static final String HCheckConf = "CheckConf";
public static final String HCheckConfFull = "CheckConfFull";
public static final String HKeyPairGen = "KeyPairGen";
public static final String HSignature = "Signature";
public static final String HSignatureVerif = "SignatureVerif";
public static final String HgetCert = "getCert";
public static final String HCerts = "Certs";
public static final String HServer = "Server";
public static final String HClient = "Client";
//вызов справки
public static final String hllp = "-help";
/**
 * HELP
 */
public static final String HELP = "HELP\n" +
        "Function" + "        Parameters\n\n" +
        HCheckConf + "        (without parameters)\n" +
        HCheckConfFull + "    <args>\n" +
        HKeyPairGen + "       args\n" +
        HSignature + "        args\n" +
        HSignatureVerif + "   args\n" +
        HgetCert + "          args\n" +
        HCerts + "            args\n" +
        HServer + "           args\n" +
        HClient + "           args\n" +
        "\nargs:\n" +
        hllp + "            call help\n" +
        "\nCall function for help...\n";

public static final String KeyPairGenHelpHD = "HELP\n" +
        "KeyPairGen \n" +
        ALIAS + "       alias                  (def: no def)\n" +
        alg + "         algorithm              (def: GOST3410)\n" +
        storetype + "   storetype              (def: HDImageStore)\n" +
        storepath + "   storepath              (def: null)\n" +
        storepass + "   storepass              (def: null)\n" +
        keypass + "     keypass                (def: null)\n" +
        isServer + "    isServer               (def: false)\n" +
        dname + "       subject of certificate (def: no def)\n" +
        reqCertpath + " C:/*.*                 (def: no def)\n" +
        encoding + "    der/base64             (def: der)\n\n" +
        hllp + "        call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String SignGenHelpHD = "HELP\n" +
        "Signature\n" +
        ALIAS + "       alias           (def: no def)\n" +
        storetype + "   storetype       (def: HDImageStore)\n" +
        storepath + "   storepath       (def: null)\n" +
        storepass + "   storepass       (def: null)\n" +
        keypass + "     keypass         (def: null)\n" +
        signpath + "    C:/*.*          (def: no def)\n" +
        filepath + "    C:/*.*          (def: no def)\n\n" +
        hllp + "        call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String SignVerHelpHD = "HELP\n" +
        "SignatureVerif\n" +
        ALIAS + "       alias           (def: no def)\n" +
        storetype + "   storetype       (def: HDImageStore)\n" +
        storepath + "   storepath       (def: null)\n" +
        storepass + "   storepass       (def: null)\n" +
        signpath + "    C:/*.*          (def: no def)\n" +
        filepath + "    C:/*.*          (def: no def)\n\n" +
        hllp + "        call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String GetCertHelpHD = "HELP\n" +
        "getCert\n" +
        ALIAS + "       alias                   (def: no def)\n" +
        storetype + "   storetype               (def: HDImageStore)\n" +
        storepath + "   storepath               (def: null)\n" +
        storepass + "   storepass               (def: null)\n" +
        http + "        center of certification (def: no def)\n" +
        reqCertpath + " C:/*.*                  (def: no def)\n" +
        certpath + "    C:/*.cer                (def: no def)\n" +
        encoding + "    der/base64              (def: der)\n\n" +
        hllp + "        call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String CertsHelpHD = "HELP\n" +
        "Certs\n" +
        ALIAS + "       alias                              (def: no def)\n" +
        storetype +
        "   storetype                          (def: HDImageStore)\n" +
        storepath + "   storepath                          (def: null)\n" +
        storepass + "   storepass                          (def: null)\n" +
        keypass + "     keypass                            (def: null)\n" +
        certs + "       C:/my.cer,C:/*.cer,...,C:/root.cer (def: no def)\n\n" +
        hllp + "        call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String HELP_SERV = "HELP\n" +
        "Server\n" +
        PORT + "               port                 (def: 443)\n" +
        auth + "               auth. of client      (def: false)\n" +
        keyStoreType + "       keyStoreType         (def: \"HDImageStore\")\n" +
        trustStoreType + "     trustStoreType       (def: \"HDImageStore\")\n" +
        trustStorePath + "     trustStorePath       (def: no def)\n" +
        trustStorePassword + " trustStorePassword   (def: no def)\n" +
        keyStorePassword + "   keyStorePassword     (def: null)\n" +
        servDir + "            serverWorkDir        (def: current)\n\n" +
        hllp + "               call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String HELP_ClIENT = "HELP\n" +
        "Client\n" +
        PORT + "               port                 (def: 443)\n" +
        SERVER + "             server name          (def: \"localhost\")\n" +
        keyStoreType + "       keyStoreType         (def: \"HDImageStore\")\n" +
        trustStoreType + "     trustStoreType       (def: \"HDImageStore\")\n" +
        trustStorePath + "     trustStorePath       (def: no def)\n" +
        trustStorePassword + " trustStorePassword   (def: no def)\n" +
        keyStorePassword + "   keyStorePassword     (def: null)\n" +
        fileget + "            name of getting file (def: index.html)\n" +
        fileout + "            path to output file  (def: out.html)\n\n" +
        hllp + "               call help\n" +
        "\n parameters with (def: no def) must be defined necessarily\n";

public static final String CheckConfFullHelp = "HELP\n" +
        "CheckConfFull\n" +
        servDir + "            serverWorkDir        (def: current)\n\n" +
        hllp + "               call help\n";

//public static final String HELP_1 = HELP + KeyPairGenHelpHD + SignGenHelpHD +
//        SignVerHelpHD + GetCertHelpHD + CertsHelpHD + HELP_SERV + HELP_ClIENT;
}
