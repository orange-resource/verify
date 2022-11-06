using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using VerifyApi;
using VerifyTest.verify.data;

namespace VerifyTest.verify
{
    internal partial class VerifyRedeem : Form
    {
        public VerifyRedeem()
        {
            InitializeComponent();
        }

        private void buttonRedeem_Click(object sender, EventArgs e)
        {
            if (textBoxAccount.Text == "")
            {
                MessageBox.Show("账号不能为空", "提示");
                return;
            }
            if (textBoxPassword.Text == "")
            {
                MessageBox.Show("密码不能为空", "提示");
                return;
            }
            if (textBoxCardNumber.Text == "")
            {
                MessageBox.Show("卡密不能为空", "提示");
                return;
            }

            try
            {
                string msg = VerifyApiLaunch.bindingCard
                    (
                        textBoxAccount.Text,
                        textBoxPassword.Text,
                        textBoxCardNumber.Text,
                        VerifyOverAll.softId,
                        VerifyOverAll.rsaPublicKey
                    );
                MessageBox.Show(msg, "充值结果");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "软件错误");
            }
        }

        ////
    }
}
