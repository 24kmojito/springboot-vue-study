import Vue from 'vue'
import { Button } from 'element-ui'
import { Form, FormItem } from 'element-ui'
import { Input } from 'element-ui'
import { Container } from 'element-ui'
import { Header } from 'element-ui'
import { Aside } from 'element-ui'
import { Main } from 'element-ui'
import { Message } from 'element-ui'
import { Menu } from 'element-ui'
import { MenuItemGroup } from 'element-ui'
import { Submenu } from 'element-ui'
import { MenuItem } from 'element-ui'
import { Breadcrumb } from 'element-ui'
import { BreadcrumbItem } from 'element-ui'
import { Link } from 'element-ui'
import {Table} from "element-ui";
import {TableColumn} from "element-ui";
import {Pagination} from "element-ui";
import {Timeline} from "element-ui";
import {TimelineItem} from "element-ui";




Vue.use(Button)
Vue.use(Timeline)
Vue.use(TimelineItem)
Vue.use(Pagination)
Vue.use(TableColumn)
Vue.use(Table)
Vue.use(Link)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Menu)
Vue.use(MenuItemGroup)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)



Vue.prototype.$message = Message
