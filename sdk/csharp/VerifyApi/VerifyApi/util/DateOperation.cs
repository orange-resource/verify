using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VerifyApi.util
{
    class DateOperation
    {
        public static string GetDateTime(long timeStamp)
        {
            DateTime dtStart = TimeZone.CurrentTimeZone.ToLocalTime(new DateTime(1970, 1, 1));
            long lTime = long.Parse(timeStamp + "0000");
            TimeSpan toNow = new TimeSpan(lTime);
            DateTime dt = dtStart.Add(toNow);
            return dt.ToLongDateString() + " " + dt.ToLongTimeString();
        }
    }
}
