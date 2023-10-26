package leet_code.easy;

/**
 * @Author zzzde
 * @Date 2023/10/23
 */
public class Test {
    /**
     * V2 版本加签验证
     * @param url String
     * @param json String?
     * @return MutableMap<String, String>
     */
    fun requestSignV2(url: String, json: String? = ""): MutableMap<String, String> {
        val param: MutableMap<String, String> = HashMap()
        param["signType"] = "2"
        param["timestamp"] = System.currentTimeMillis().toString()
        if (!json.isNullOrEmpty()) {
            param["requestBody"] = json.base64Encode()
        }
        param["uri"] = url.substring(url.lastIndexOf("/") + 1)
        param["sign"] = sign2(param) + ""
        param.remove("uri")
        if (!json.isNullOrEmpty()) {
            param.remove("requestBody")
        }
        return param
    }

    /**
     * V2 生成签名方法
     * @param param Map<String, String?>
     * @return String?
     */
    private fun sign2(param: Map<String, String?>): String? {
        val keys: List<String> = ArrayList(param.keys)
        Collections.sort(keys)
        val toSign = StringBuffer()
        for (key in keys) {
            val value = param[key]
            if (null != value && "" != value && "sign" != key && "key" != key) {
                toSign.append("$key=$value&")
            }
        }
        toSign.append("key=75efe0b4f9264fa59f31533243756ffb")
//        "签名字符串：${toSign.toString()}".d()
//        toSign.setLength(toSign.length - 1)
        return toSign.toString().md5()
    }
}
