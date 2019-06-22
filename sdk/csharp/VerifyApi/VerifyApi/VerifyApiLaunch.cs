using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using VroomJs;
using System.Reflection;
using System.Resources;
using VerifyApi.util;
using excleanalysis.visit;
using System.Net;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Drawing;

namespace VerifyApi
{
    public class VerifyApiLaunch
    {
        // 访问前缀地址
        public static string site = "";

        // 机器码
        private static string machineCode = MachineCodeUtil.getCode();

        /// <summary>
        /// 临时方法
        /// </summary>
        /// <returns></returns>
        public static string Test()
        {
            return machineCode;
        }

        /// <summary>
        /// 登陆
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="softId">软件ID</param>
        /// <param name="rsaPublicKey">Rsa公钥</param>
        /// <returns>登陆结果</returns>
        public static string login
            (
                string username,
                string password,
                string softId,
                string rsaPublicKey
            )
        {
            string passwordEncrypt = VerifyApiLaunch.RsaEncrypt(password, rsaPublicKey);
            string machineCode = VerifyApiLaunch.RsaEncrypt(VerifyApiLaunch.machineCode, rsaPublicKey);

            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", passwordEncrypt);
            data.Add("softId", softId);
            data.Add("code", machineCode);
            data.Add("publicKey", rsaPublicKey);

            string api = VerifyApiLaunch.site + "/account/login";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject) JsonConvert.DeserializeObject(response);

            int code = (int) resultJson["code"];
            if (code == 100)
            {
                return "登陆成功";
            }

            return (string)resultJson["msg"];
        }

        /// <summary>
        /// 注册
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="qq">QQ号</param>
        /// <param name="softId">软件ID</param>
        /// <param name="name">姓名</param>
        /// <param name="securityCode">安全码</param>
        /// <param name="rsaPublicKey">公钥</param>
        /// <param name="vc">验证码</param>
        /// <returns>返回注册结果</returns>
        public static string register
            (
                string username,
                string password,
                string qq,
                string softId,
                string name,
                string securityCode,
                string rsaPublicKey,
                string vc
            )
        {
            string passwordEncrypt = VerifyApiLaunch.RsaEncrypt(password, rsaPublicKey);
            string machineCode = VerifyApiLaunch.RsaEncrypt(VerifyApiLaunch.machineCode, rsaPublicKey);

            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", passwordEncrypt);
            data.Add("qq", qq);
            data.Add("softId", softId);
            data.Add("name", name);
            data.Add("code", machineCode);
            data.Add("securityCode", securityCode);
            data.Add("publicKey", rsaPublicKey);
            data.Add("vc", vc);

            string api = VerifyApiLaunch.site + "/account/register";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 101)
            {
                return "注册成功";
            }

            return (string)resultJson["msg"];
        }

        /// <summary>
        /// 绑定卡密 (充值)
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="cardNumber">卡密</param>
        /// <param name="softId">软件ID</param>
        /// <param name="rsaPublicKey">Rsa公钥</param>
        /// <returns></returns>
        public static string bindingCard
            (
                string username,
                string password,
                string cardNumber,
                string softId,
                string rsaPublicKey
            )
        {
            string passwordEncrypt = VerifyApiLaunch.RsaEncrypt(password, rsaPublicKey);
            string machineCode = VerifyApiLaunch.RsaEncrypt(VerifyApiLaunch.machineCode, rsaPublicKey);

            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", passwordEncrypt);
            data.Add("softId", softId);
            data.Add("code", machineCode);
            data.Add("publicKey", rsaPublicKey);
            data.Add("cardNumber", cardNumber);

            string api = VerifyApiLaunch.site + "/account/bindingCard";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 102)
            {
                return "充值成功";
            }

            return (string)resultJson["msg"];
        }

        /// <summary>
        /// 绑定机器
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="softId">软件ID</param>
        /// <param name="rsaPublicKey">Rsa公钥</param>
        /// <returns></returns>
        public static string bindingMachineCode
            (
                string username,
                string password,
                string softId,
                string rsaPublicKey
            )
        {
            string passwordEncrypt = VerifyApiLaunch.RsaEncrypt(password, rsaPublicKey);
            string machineCode = VerifyApiLaunch.RsaEncrypt(VerifyApiLaunch.machineCode, rsaPublicKey);

            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", passwordEncrypt);
            data.Add("softId", softId);
            data.Add("code", machineCode);
            data.Add("publicKey", rsaPublicKey);

            string api = VerifyApiLaunch.site + "/account/bindingCode";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 103)
            {
                return "绑定机器成功";
            }

            return (string)resultJson["msg"];
        }

        /// <summary>
        /// 修改密码
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">新密码</param>
        /// <param name="softId">软件ID</param>
        /// <param name="securityCode">安全码</param>
        /// <returns></returns>
        public static string updatePassword
            (
                string username,
                string password,
                string softId,
                string securityCode
            )
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", password);
            data.Add("softId", softId);
            data.Add("securityCode", securityCode);

            string api = VerifyApiLaunch.site + "/account/updatePassword";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 105)
            {
                return "修改密码成功";
            }

            return (string)resultJson["msg"];
        }

        /// <summary>
        /// 取验证码
        /// </summary>
        /// <param name="rsaPublicKey">Rsa公钥</param>
        /// <returns></returns>
        public static Image getVerificationCode(string rsaPublicKey)
        {
            string api = VerifyApiLaunch.site + "/account/getVerificationCode?publicKey=" + rsaPublicKey;

            Image image = OrangeRequest.Get(api).LaunchToImage();

            return image;
        }

        /// <summary>
        /// 取卡密期限
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="softId">软件ID</param>
        /// <param name="startDate">开始时间</param>
        /// <param name="endDate">结束时间</param>
        public static void getCardTimeLimit
            (
                string username,
                string password,
                string softId,
                out string startDate,
                out string endDate
            )
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("username", username);
            data.Add("password", password);
            data.Add("softId", softId);

            string api = VerifyApiLaunch.site + "/card/getCardTimeLimit";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 9)
            {
                startDate = DateOperation.GetDateTime((long)resultJson["data"]["startDate"]);
                endDate = DateOperation.GetDateTime((long)resultJson["data"]["endDate"]);
            }
            else
            {
                throw (new Exception((string)resultJson["msg"]));
            }
        }

        /// <summary>
        /// 获取软件信息
        /// </summary>
        /// <param name="softId">软件ID</param>
        /// <param name="notice">公告</param>
        /// <param name="name">软件名称</param>
        /// <param name="dosingStrategy">多开策略</param>
        /// <param name="registerStatus">注册状态</param>
        /// <param name="registeCloseMsg">关闭注册公告</param>
        /// <param name="serviceStatus">软件服务状态</param>
        /// <param name="serviceCloseMsg">关闭服务公告</param>
        /// <param name="changeStrategy">是否支持换绑定机器</param>
        public static void getSoftDesc
            (
                string softId,
                out string notice,
                out string name,
                out int dosingStrategy,
                out int registerStatus,
                out string registeCloseMsg,
                out int serviceStatus,
                out string serviceCloseMsg,
                out int changeStrategy
            )
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("softId", softId);

            string api = VerifyApiLaunch.site + "/soft/getSoftDesc";

            string response = OrangeRequest.Post(api).AddData(data).LaunchToString();

            JObject resultJson = (JObject)JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 9 || code == 907)
            {
                notice = (string)resultJson["data"]["notice"];
                name = (string)resultJson["data"]["name"];
                dosingStrategy = (int)resultJson["data"]["dosingStrategy"];
                registerStatus = (int)resultJson["data"]["registerStatus"];
                registeCloseMsg = (string)resultJson["data"]["registeCloseMsg"];
                serviceStatus = (int)resultJson["data"]["serviceStatus"];
                serviceCloseMsg = (string)resultJson["data"]["serviceCloseMsg"];
                changeStrategy = (int)resultJson["data"]["changeStrategy"];
            }
            else
            {
                throw (new Exception((string)resultJson["msg"]));
            }
        }

        /// <summary>
        /// 获取Rsa公钥
        /// </summary>
        /// <returns>返回公钥</returns>
        public static string getRsaPublicKey()
        {
            string api = VerifyApiLaunch.site + "/account/getPublicKey";

            string response = OrangeRequest.Post(api).LaunchToString();

            JObject resultJson = (JObject) JsonConvert.DeserializeObject(response);

            int code = (int)resultJson["code"];
            if (code == 9)
            {
                return (String)resultJson["data"];
            }
            else
            {
                throw (new Exception((string)resultJson["msg"]));
            }
        }

        /// <summary>
        /// RSA加密
        /// </summary>
        /// <param name="value">要加密的值</param>
        /// <param name="rsaPublic">rsa公钥</param>
        /// <returns>返回加密后的密码</returns>
        private static string RsaEncrypt(string value, string rsaPublic)
        {
            // 初始化vroom js 才可以工作
            VroomJs.AssemblyLoader.EnsureLoaded(); // windows only

            using (JsEngine engine = new JsEngine())
            {
                using (var context = engine.CreateContext())
                {
                    string encryptJs = Properties.Resources.encrypt;

                    string encryptValue = (string) context.Execute(encryptJs + "encrypt('"+ value + "', '" + rsaPublic + "')");

                    return encryptValue;
                }
            }
        }

        ////////
    }
}
