var menus = [
  {
    action: "home",
    title: "首页",
    path:"/index",
    items: [{ title: "动态", path: "/dynamic" }]
  },
  {
    action: "apps",
    title: "信息发布",
    path:"/item",
    items: [
      { title: "公告信息", path: "/category" },
      { title: "新增信息", path: "/brand" },

    ]
  },
  {
    action: "people",
    title: "宿舍管理",
    path:"/item",
    items: [
      // { title: "晚归登记", path: "/dormitory" },
      { title: "迁入迁出", path: "/list" }
    ]
  },
  {
    action: "attach_money",
    title: "维修报备",
    path:"/item",
    items: [
      { title: "维修任务", path: "/repair" },
      { title: "新增维修任务", path: "/addRepair" },
      // { title: "物流管理", path: "/logistics" },
      // { title: "促销管理", path: "/promotion" }
    ]
  },
  {
    action: "settings",
    title: "权限管理",
    path:"/authority",
    items: [
      { title: "用户信息", path: "/info" },
      { title: "权限管理", path: "/role" },
      // { title: "人员管理", path: "/member" }
    ]
  }
]

export default menus;
