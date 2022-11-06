using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace VerifyTest.verify.data
{
    public class InitVerify
    {
        /// <summary>
        /// 访问网址
        /// </summary>
        public string Site { get; set; }

        /// <summary>
        /// 软件ID
        /// </summary>
        public string SoftId { get; set; }

        /// <summary>
        /// 登陆成功后打开的窗体
        /// </summary>
        public Form RunForm { get; set; }
    }
}
