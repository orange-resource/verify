﻿//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由工具生成。
//     运行时版本:4.0.30319.42000
//
//     对此文件的更改可能会导致不正确的行为，并且如果
//     重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------

namespace VerifyApi.Properties {
    using System;
    
    
    /// <summary>
    ///   一个强类型的资源类，用于查找本地化的字符串等。
    /// </summary>
    // 此类是由 StronglyTypedResourceBuilder
    // 类通过类似于 ResGen 或 Visual Studio 的工具自动生成的。
    // 若要添加或移除成员，请编辑 .ResX 文件，然后重新运行 ResGen
    // (以 /str 作为命令选项)，或重新生成 VS 项目。
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("System.Resources.Tools.StronglyTypedResourceBuilder", "15.0.0.0")]
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    public class Resources {
        
        private static global::System.Resources.ResourceManager resourceMan;
        
        private static global::System.Globalization.CultureInfo resourceCulture;
        
        [global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal Resources() {
        }
        
        /// <summary>
        ///   返回此类使用的缓存的 ResourceManager 实例。
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Resources.ResourceManager ResourceManager {
            get {
                if (object.ReferenceEquals(resourceMan, null)) {
                    global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("VerifyApi.Properties.Resources", typeof(Resources).Assembly);
                    resourceMan = temp;
                }
                return resourceMan;
            }
        }
        
        /// <summary>
        ///   重写当前线程的 CurrentUICulture 属性
        ///   重写当前线程的 CurrentUICulture 属性。
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Globalization.CultureInfo Culture {
            get {
                return resourceCulture;
            }
            set {
                resourceCulture = value;
            }
        }
        
        /// <summary>
        ///   查找类似 
        ///function encrypt(enValue,enPublicKey){var navigator=this;var window=this;(function(global,factory){typeof exports===&apos;object&apos;&amp;&amp;typeof module!==&apos;undefined&apos;?factory(exports):typeof define===&apos;function&apos;&amp;&amp;define.amd?define([&apos;exports&apos;],factory):(factory((global.JSEncrypt={})));}(this,(function(exports){&apos;use strict&apos;;var BI_RM=&quot;0123456789abcdefghijklmnopqrstuvwxyz&quot;;function int2char(n){return BI_RM.charAt(n);}function op_and(x,y){return x&amp;y;}function op_or(x,y){return x|y;}function op_xor(x,y){return x^y;}function [字符串的其余部分被截断]&quot;; 的本地化字符串。
        /// </summary>
        public static string encrypt {
            get {
                return ResourceManager.GetString("encrypt", resourceCulture);
            }
        }
        
        /// <summary>
        ///   查找类似 http://verifyopen.wywxy.top/verify 的本地化字符串。
        /// </summary>
        public static string site {
            get {
                return ResourceManager.GetString("site", resourceCulture);
            }
        }
    }
}