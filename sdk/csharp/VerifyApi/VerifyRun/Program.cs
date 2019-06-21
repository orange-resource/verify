using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using VerifyTest.verify;
using VerifyTest.verify.data;

namespace VerifyRun
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            InitVerify initVerify =new InitVerify();
            initVerify.Site = "http://localhost:80/verify";
            initVerify.SoftId = "1142171718408757250";
            Application.Run(new VerifyForm(initVerify));
        }
    }
}
