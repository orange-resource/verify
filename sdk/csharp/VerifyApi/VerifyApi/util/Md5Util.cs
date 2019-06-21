using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VerifyApi.util
{
    class Md5Util
    {
        public static string GetMd5(string value)
        {
            return System.Web.Security.FormsAuthentication.HashPasswordForStoringInConfigFile(value, "MD5").ToLower();
        }
    }
}
