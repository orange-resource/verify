using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace VerifyTest.verify.util
{
    class FileOp
    {
        public static void WriteFile(string path)
        {
            if (!System.IO.File.Exists(path))
            {
                string url = path.Substring(0, path.LastIndexOf('/')); ;
                Directory.CreateDirectory(url);
            }
            System.IO.StreamWriter f2 = new System.IO.StreamWriter(path, true, System.Text.Encoding.UTF8);
            f2.Close();
            f2.Dispose();

        }
    }
}
