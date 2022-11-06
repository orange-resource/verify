using System.Dynamic;
using System.Threading;
using System.Windows.Forms;

namespace Loading
{
    public class LoadingHelper
    {
        /// <summary>
        /// 开始加载
        /// </summary>
        /// <param name="message">消息</param>
        /// <param name="ownerForm">父窗体</param>
        /// <param name="work">待执行工作</param>
        /// <param name="workArg">工作参数</param>
        public static void ShowLoading(string message, Form ownerForm, ParameterizedThreadStart work, object workArg = null)
        {
            var loadingForm = new FrmLoading(message);
            dynamic expandoObject = new ExpandoObject();
            expandoObject.Form = loadingForm;
            expandoObject.WorkArg = workArg;
            loadingForm.SetWorkAction(work, expandoObject);
            loadingForm.ShowDialog(ownerForm);
            if (loadingForm.WorkException != null)
            {
                throw loadingForm.WorkException;
            }
        }
    }
}
