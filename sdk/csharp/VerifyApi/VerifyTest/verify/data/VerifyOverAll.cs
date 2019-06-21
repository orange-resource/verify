using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace VerifyTest.verify.data
{
    class VerifyOverAll
    {
        /// <summary>
        /// 访问网址
        /// </summary>
        public static string site = "http://verifyopen.wywxy.top/verify";

        /// <summary>
        /// 软件ID
        /// </summary>
        public static string softId = "1085459168090959873";

        /// <summary>
        /// 登陆成功后打开的窗体
        /// </summary>
        public static Form runForm;

        /// <summary>
        /// rsaPublicKey
        /// </summary>
        public static string rsaPublicKey = "";

        /// <summary>
        /// 软件信息相关
        /// </summary>
        public static string notice = "";
        public static string name = "";
        public static int dosingStrategy;
        public static int registerStatus;
        public static string registeCloseMsg = "";
        public static int serviceStatus;
        public static string serviceCloseMsg = "";
        public static int changeStrategy;
    }
}
