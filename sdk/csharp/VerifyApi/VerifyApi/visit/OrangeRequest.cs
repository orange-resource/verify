using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using excleanalysis.visit;
using System.IO;
using System.Drawing;

namespace excleanalysis.visit
{
    class OrangeRequest
    {
        private String url = "";
        private String method = "";
        private String data = "";
        private Dictionary<String,String> headers = new Dictionary<String,String>();
        private int timeOut = 60 * 1000;
        private bool allowAutoRedirect = true;

        private OrangeRequest(String url,String method)
        {
            this.url = url;
            this.method = method;
        }

        public static OrangeRequest Get(String url)
        {
            return new OrangeRequest(url,Method.GET);
        }

        public static OrangeRequest Post(String url)
        {
            return new OrangeRequest(url,Method.POST);
        }

        public OrangeRequest TimeOut(int second)
        {
            this.timeOut = second * 1000;
            return this;
        }

        public OrangeRequest AllowAutoRedirect(bool allowAutoRedirect)
        {
            this.allowAutoRedirect = allowAutoRedirect;
            return this;
        }

        public OrangeRequest AddHeader(String name,String value)
        {
            this.headers.Add(name,value);
            return this;
        }

        public OrangeRequest AddCookie(String cookie)
        {
            this.headers.Add("Cookie", cookie);
            return this;
        }

        public OrangeRequest AddData(String data)
        {
            this.data = data;
            return this;
        }

        public OrangeRequest AddData(Dictionary<string,string> data)
        {
            string joint = "";
            for (int i = 0; i < data.Count; i++)
            {
                if (i == data.Count - 1)
                {
                    joint += data.ToList()[i].Key + "=" + data.ToList()[i].Value;
                }
                else
                {
                    joint += data.ToList()[i].Key + "=" + data.ToList()[i].Value + "&";
                }
            }
            this.data = joint;
            return this;
        }

        public Image LaunchToImage()
        {
            HttpWebResponse response = Launch();

            return new System.Drawing.Bitmap(response.GetResponseStream());
        }

        public string LaunchToString()
        {
            return LaunchToString("utf-8");
        }

        public string LaunchToString(string encode)
        {
            HttpWebResponse response = Launch();

            StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding(encode));

            string data = reader.ReadToEnd();

            return data;
        }

        public HttpWebResponse Launch()
        {
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(this.url);
            httpWebRequest.Method = this.method;
            httpWebRequest.Timeout = this.timeOut;
            httpWebRequest.AllowAutoRedirect = this.allowAutoRedirect;

            httpWebRequest.Accept = "*/*";
            httpWebRequest.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36";
            if (this.method == Method.POST)
            {
                httpWebRequest.ContentType = "application/x-www-form-urlencoded";
            }
            httpWebRequest.Referer = this.url;

            foreach (KeyValuePair<String,String> item in this.headers)
            {
                switch (item.Key)
                {
                    case "Content-Type":
                        httpWebRequest.ContentType = item.Value;
                        break;
                    case "Accept":
                        httpWebRequest.Accept = item.Value;
                        break;
                    case "Referer":
                        httpWebRequest.Referer = item.Value;
                        break;
                    case "User-Agent":
                        httpWebRequest.UserAgent = item.Value;
                        break;
                    default:
                        httpWebRequest.Headers.Add(item.Key, item.Value);
                        break;
                }
            }

            if (this.data != "" && this.method == Method.POST)
            {
                byte[] dataByte = Encoding.UTF8.GetBytes(this.data);
                httpWebRequest.ContentLength = dataByte.Length;
                Stream dataStream = httpWebRequest.GetRequestStream();
                dataStream.Write(dataByte, 0, dataByte.Length);
                dataStream.Close();
            }

            return (HttpWebResponse) httpWebRequest.GetResponse();
        }

        ///////
    }
}
