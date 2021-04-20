# CustomComponent
自定义组件（空视图、标题、表单输入项、设置项、菜单项、按钮、文字），我们项目开发中有时候可能会同时维护多个项目，而这些项目间又有很多相似的UI组件，如果从0到1一个个编写或者写本地自定义view，将会浪费大量时间和精力，所以将这些组件发布到公共仓库统一维护和依赖，旨在让我们专注于功能开发，毕竟写UI很枯燥，而且对自身的技术也没有任何提升。这些组件有直接继承view绘制的，也有继承系统组件进行二次开发的，项目中的不足，广大前辈们可随时提Issues。

<img src="https://gitee.com/dayun220/private-img/raw/master/IMG_20210420_165330.jpg" width="360" height="720" alt="菜单预览"/><br/>


# EmptyView
空数据视图-（图标加文字描述）
继承于ConstraintLayout

<img src="https://gitee.com/dayun220/private-img/raw/master/IMG_20210420_165305.jpg" width="360" height="720" alt="空数据视图"/><br/>


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

# FormView
表单输入项
## 属性表格
| name | format | description |
| ------ | ------ | ------ |
| form_name | string | 表单名称 |
| form_name_text_size | dimension | 名称字体大小 |
| form_name_text_color | color | 名称字体颜色 |
| form_name_text_style | enum | 名称字体风格（BOLD、NORMAL），默认BOLD |
| form_unit | string | 表单输入框后的单位（人、元、张等等）|
| form_unit_text_size | dimension | 单位字体大小 |
| form_unit_text_color | color | 单位字体颜色 |
| form_end_icon | reference | 表单输入框后的图标显示（默认显示右箭头图标且隐藏），xml中设置了就会visible |
| form_bottom_line_gone | boolean | 整个表单底部的分割线，默认true，即不隐藏 |
| form_bottom_line_color | color | 分割线颜色 |
| form_bottom_line_margin_start | dimension | 分割线距起始位置的间距 |
| form_edit_hint | string | 默认显示文字 |
| form_edit_input_enable | boolean | 设置表单是否可输入，默认true，false时，父控件将会拦截触摸事件 |
| form_edit_hint_color | color | 默认文字的字体颜色 |
| form_edit_text_size | dimension | 输入框字体大小 |
| form_edit_text_color | color | 输入框字体颜色|
| form_edit_text | string | 输入框显示文字 |
| form_edit_text_style | enum | 输入框文字字体风格（BOLD、NORMAL），默认NORMAL |
| form_edit_max_length | integer | 输入框字数限制 |
| form_edit_input_type | integer | 输入框可输入的文字类型（TYPE_CLASS_NUMBER、TYPE_CLASS_PHONE、TYPE_NUMBER_FLAG_DECIMAL） |
| form_background_color | color | 整个表单的背景色 |


