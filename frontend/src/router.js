
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ConnectLogServiceConnectLogManager from "./components/listers/ConnectLogServiceConnectLogCards"
import ConnectLogServiceConnectLogDetail from "./components/listers/ConnectLogServiceConnectLogDetail"

import ItemCodeServiceItemCodeManager from "./components/listers/ItemCodeServiceItemCodeCards"
import ItemCodeServiceItemCodeDetail from "./components/listers/ItemCodeServiceItemCodeDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/connectLogServices/connectLogs',
                name: 'ConnectLogServiceConnectLogManager',
                component: ConnectLogServiceConnectLogManager
            },
            {
                path: '/connectLogServices/connectLogs/:id',
                name: 'ConnectLogServiceConnectLogDetail',
                component: ConnectLogServiceConnectLogDetail
            },

            {
                path: '/itemCodeServices/itemCodes',
                name: 'ItemCodeServiceItemCodeManager',
                component: ItemCodeServiceItemCodeManager
            },
            {
                path: '/itemCodeServices/itemCodes/:id',
                name: 'ItemCodeServiceItemCodeDetail',
                component: ItemCodeServiceItemCodeDetail
            },



    ]
})
