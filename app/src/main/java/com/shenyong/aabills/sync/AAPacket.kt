package com.shenyong.aabills.sync

import com.alibaba.fastjson.JSON

/**
 *  处理局域网账单同步的数据结构
 * @author ShenYong
 * @date 2018/11/26
 */
data class AAPacket(
        var type: Int = 0,
        var orgIp: String = "",
        var dstIp: String = "",
        var orgUid: String = "",
        var dstUid: String = "",
        var data: String = "") {

    companion object {
        const val TYPE_SYNC = 1
        const val TYPE_DATA = 2
        const val TYPE_USER = 3
        // 扫描局域网用户请求，接收方返回自己信息
        const val TYPE_SCAN = 4

        /**
         * 构造一个同步请求包
         */
        fun syncPacket(orgIp: String, orgUid: String): AAPacket {
            return AAPacket(type = TYPE_SYNC, orgIp = orgIp, orgUid = orgUid)
        }

        /**
         * 构造一个扫描用户请求包
         */
        fun scanPacket(orgIp: String, orgUid: String): AAPacket {
            return AAPacket(type = TYPE_SCAN, orgIp = orgIp, orgUid = orgUid)
        }

        /**
         * 构造一个账单数据包
         */
        fun dataPacket(orgIp: String, orgUid: String): AAPacket {
            return AAPacket(type = TYPE_DATA, orgIp = orgIp, orgUid = orgUid)
        }

        /**
         * 构造一个账单数据包
         */
        fun userPacket(orgIp: String, orgUid: String): AAPacket {
            return AAPacket(type = TYPE_USER, orgIp = orgIp, orgUid = orgUid)
        }

        fun jsonToPacket(json: String): AAPacket {
            try {
                return JSON.parseObject(json, AAPacket::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return AAPacket()
        }
    }

    fun toJSONString(): String {
        return JSON.toJSONString(this)
    }
}