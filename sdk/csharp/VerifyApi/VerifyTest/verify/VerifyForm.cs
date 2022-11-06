using AutoUpdaterDotNET;
using IniParser;
using IniParser.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using VerifyApi;
using VerifyTest.verify.data;
using VerifyTest.verify.util;
using Loading;

namespace VerifyTest.verify
{
    public partial class VerifyForm : Form
    {

        private FileIniDataParser fileIniConfig = new FileIniDataParser();

        private const string INI_FILE = "data/verify/verify.ini";

        public VerifyForm(InitVerify initVerify)
        {
            InitializeComponent();

            VerifyOverAll.site = initVerify.Site;
            VerifyOverAll.softId = initVerify.SoftId;
            VerifyOverAll.runForm = initVerify.RunForm;

            LoadingHelper.ShowLoading("正在初始化软件.....请耐心稍等....", this, o =>
            {
                try
                {
                    VerifyApiLaunch.site = VerifyOverAll.site;

                    VerifyOverAll.rsaPublicKey = VerifyApiLaunch.getRsaPublicKey();

                    VerifyApiLaunch.getSoftDesc
                    (
                        VerifyOverAll.softId,
                        out VerifyOverAll.notice,
                        out VerifyOverAll.name,
                        out VerifyOverAll.dosingStrategy,
                        out VerifyOverAll.registerStatus,
                        out VerifyOverAll.registeCloseMsg,
                        out VerifyOverAll.serviceStatus,
                        out VerifyOverAll.serviceCloseMsg,
                        out VerifyOverAll.changeStrategy
                    );

                    if (VerifyOverAll.serviceStatus == 2)
                    {
                        MessageBox.Show(VerifyOverAll.serviceCloseMsg.Replace("\n", "\r\n"), "软件开放使用提示");
                        System.Environment.Exit(0);
                    } else if (VerifyOverAll.serviceStatus == 0)
                    {
                        toolStripStatusLabel2.Text = "软件类型：收费软件";
                    } else if (VerifyOverAll.serviceStatus == 1)
                    {
                        toolStripStatusLabel2.Text = "软件类型：免费软件";
                    }
                    if (VerifyOverAll.serviceStatus == 0)
                    {
                        linkLabelOpenRecharge.Visible = true;
                    }
                    if (VerifyOverAll.changeStrategy == 0)
                    {
                        linkLabelOpenExchange.Visible = true;
                    }

                    this.Text = VerifyOverAll.name;
                    textBoxNotice.Text = VerifyOverAll.notice.Replace("\n", "\r\n");
                }
                catch (Exception e)
                {
                    MessageBox.Show(e.Message, "未知错误");
                    System.Environment.Exit(0);
                }
            });

            // 初始化配置
            try
            {
                checkBoxRemember.Checked = Convert.ToBoolean(fileIniConfig.ReadFile(INI_FILE)["input"]["remember"]);
                if (checkBoxRemember.Checked == true)
                {
                    textBoxAccount.Text = fileIniConfig.ReadFile(INI_FILE)["input"]["account"];
                    textBoxPassword.Text = fileIniConfig.ReadFile(INI_FILE)["input"]["password"];
                }
            }
            catch (Exception ex)
            {
                FileOp.WriteFile(INI_FILE);
            }

        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            if (textBoxAccount.Text == "")
            {
                MessageBox.Show("用户名不能为空", "提示");
                return;
            }
            if (textBoxPassword.Text == "")
            {
                MessageBox.Show("密码不能为空", "提示");
                return;
            }

            try
            {
                if (checkBoxRemember.Checked == true)
                {
                    try
                    {
                        IniData data = fileIniConfig.ReadFile(INI_FILE);
                        data["input"]["account"] = textBoxAccount.Text;
                        data["input"]["password"] = textBoxPassword.Text;
                        fileIniConfig.WriteFile(INI_FILE, data);
                    }
                    catch (Exception ex)
                    {
                    }
                }

                    string msg = VerifyApiLaunch.login
                    (
                        textBoxAccount.Text,
                        textBoxPassword.Text,
                        VerifyOverAll.softId,
                        VerifyOverAll.rsaPublicKey
                    );
                if (msg == "登陆成功")
                {
                    if (VerifyOverAll.serviceStatus == 0) // 收费
                    {
                        try
                        {
                            VerifyApiLaunch.getCardTimeLimit
                                (
                                    textBoxAccount.Text,
                                    textBoxPassword.Text,
                                    VerifyOverAll.softId,
                                    out string startDate,
                                    out string endDate
                                );
                            string msgCard = "卡密开始使用时间：" + startDate + "\r\n" + "卡密到期时间：" + endDate;
                            MessageBox.Show(msgCard, "卡密时长");
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show(ex.Message, "软件错误");
                        }
                    }
                    // 载入窗口
                    VerifyOverAll.runForm.Show();

                    // 主窗口隐藏
                    this.Hide();
                }
                else
                {
                    MessageBox.Show(msg, "登陆结果");
                }
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "软件错误");
            }
        }

        private void linkLabelOpenRegister_Click(object sender, EventArgs e)
        {
            if (VerifyOverAll.registerStatus == 0)
            {
                VerifyRegisterForm verifyRegisterForm = new VerifyRegisterForm();
                verifyRegisterForm.ShowDialog();
            }
            else
            {
                MessageBox.Show(VerifyOverAll.registeCloseMsg.Replace("\n", "\r\n"), "注册通知");
            }
        }

        private void linkLabelOpenUpdatePassword_Click(object sender, EventArgs e)
        {
            VerifyUpdatePassword verifyUpdatePassword = new VerifyUpdatePassword();
            verifyUpdatePassword.ShowDialog();
        }

        private void linkLabelOpenExchange_Click(object sender, EventArgs e)
        {
            VerifyBinding verifyBinding = new VerifyBinding();
            verifyBinding.ShowDialog();
        }

        private void linkLabelOpenRecharge_Click(object sender, EventArgs e)
        {
            VerifyRedeem verifyRedeem = new VerifyRedeem();
            verifyRedeem.ShowDialog();
        }

        private void checkBoxRemember_Click(object sender, EventArgs e)
        {
            try
            {
                IniData data = fileIniConfig.ReadFile(INI_FILE);
                data["input"]["remember"] = checkBoxRemember.Checked == true ? "true" : "false";
                fileIniConfig.WriteFile(INI_FILE, data);
            }
            catch (Exception ex)
            {
            }
        }

        private void linkLabelOpenOrangeSite_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("https://github.com/kylelin1998");
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            VerifyReward verifyReward = new VerifyReward();
            verifyReward.ShowDialog();
        }

        private void VerifyForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            System.Environment.Exit(0);
        }

        /////////
    }
}
