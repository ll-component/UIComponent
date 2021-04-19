# CustomComponent
自定义组件（空视图、标题、表单输入项、设置项、菜单项、按钮、文字），我们项目开发中有时候可能会同时维护多个项目，而这些项目间又有很多相似的UI组件，如果从0到1一个个编写或者写本地自定义view，将会浪费大量时间和精力，所以将这些组件发布到公共仓库统一维护和依赖，旨在让我们专注于功能开发，毕竟写UI很枯燥，而且对自身的技术也没有任何提升。这些组件有直接继承view绘制的，也有继承系统组件进行二次开发的，项目中的不足，广大前辈们可随时提Issues。


# EmptyView
空数据视图-（图标加文字描述）
继承于ConstraintLayout

![空视图预览](https://github.com/ll-component/private-img/blob/master/empty_view_preview.png)

## 属性表格

| name | format | description |
| ------ | ------ | ------ |
| empty_title | string | 提示文字（例如：暂无数据） |
| empty_icon | reference | 提示图标 |
| empty_icon_width | dimension | 图标宽 |
| empty_icon_height | dimension | 图标高 |
| empty_title_topToBottom_icon | boolean | 文字相对于图标是否使用topToBottom属性，默认false，即bottomToBottom |
| empty_title_topToBottom_margin | dimension | 文字相对于图标topToBottom属性时，二者之间的间距 |
| empty_title_bottomToBottom_margin | dimension | 文字相对于图标bottomToBottom属性时，二者之间的间距 |
