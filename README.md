# UIComponent
自定义组件（空视图、标题、表单输入项、设置项、菜单项、按钮、文字），我们项目开发中有时候可能会同时维护多个项目，而这些项目间又有很多相似的UI组件，如果从0到1一个个编写或者写本地自定义view，将会浪费大量时间和精力，所以将这些组件发布到公共仓库统一维护和依赖，旨在让我们专注于功能开发。这些组件有直接继承view绘制的，也有继承系统组件进行二次开发的。组件后续会一直进行增加……

gitee仓库地址： [https://gitee.com/dayun220/UIComponent](https://gitee.com/dayun220/UIComponent)

依赖方式：

最新版本号可查看：[https://github.com/ll-component/UIComponent/releases]

Step 1. root build.gradle：
```
allprojects {
		
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. dependency
```
dependencies {
	        
	implementation 'com.github.ll-component:UIComponent:1.3.2'
	}
```

<img src="https://gitee.com/dayun220/private-img/raw/master/00.jpg" width="360" height="720" alt="菜单预览"/><br/>


# EmptyView
空数据视图-（图标加文字描述）
继承于ConstraintLayout

<img src="https://gitee.com/dayun220/private-img/raw/master/01.jpg" width="360" height="720" alt="空数据视图"/><br/>


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
表单输入项，一般用于商品添加，个人信息编辑等场景，也可禁用掉输入框当做普通的菜单入口项，禁用的话设置form_edit_input_enable属性为false，这时parent会拦截触摸事件

<img src="https://gitee.com/dayun220/private-img/raw/master/02.jpg" width="360" height="720" alt="空数据视图"/><br/>

## 代码示例

获取表单的输入框：
```
formView.getEditText();
```

获取表单输入框中的内容：
```
String text = formView.getInputStr();
```

设置表单是否可输入：
```
formView.setFormInputEnable(false);
```

设置表单的name：
```
formView.setFormName("性别")
```

设置表单的单位：
```
formView.setFormUnit("张")
```

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

# ShapeTextView

支持xml直接声明shape背景的TextView

<img src="https://gitee.com/dayun220/private-img/raw/master/03.jpg" width="360" height="720" alt="空数据视图"/><br/>

## 属性表格
| name | format | description |
| ------ | ------ | ------ |
| shape | integer | RECTANGLE、OVAL |
| shapeRadius | dimension | 圆角大小 |
| shapeTopLeftRadius | dimension | 左上角圆角大小 |
| shapeTopRightRadius | dimension | 右上角圆角大小 |
| shapeBottomLeftRadius | dimension | 左下角圆角大小 |
| shapeBottomRightRadius | dimension | 右下角圆角大小 |
| shapeStrokeWidth | dimension | 设置边框线宽 |
| shapeSolidColor | color | 背景填充色 |
| shapeStrokeColor | color | 边框线的颜色 |
| shapeStrokeStartColor | color | 设置边框渐变色start |
| shapeStrokeEndColor | color | 设置边框渐变色end |
| shapeStartColor | color | 设置背景填充渐变色start |
| shapeEndColor | color | 设置背景填充渐变色end |
| shapeColorOrientation | integer | 颜色渐变的方向 |

# RotateTextView

支持旋转角度的TextView

<img src="https://gitee.com/dayun220/private-img/raw/master/IMG_20210420_165200.jpg" width="360" height="720" alt="空数据视图"/><br/>

## 属性表格
| name | format | description |
| ------ | ------ | ------ |
| rt_degrees | integer | 旋转角度0~360 |

# ClearEditTextView

带清除按钮的输入框且可xml声明shape背景属性

<img src="https://gitee.com/dayun220/private-img/raw/master/05.jpg" width="360" height="720" alt="输入框"/><br/>

## 属性表格
| name | format | description |
| ------ | ------ | ------ |
| searchClearIcon | reference | 输入框end位置的图标、清除按钮的图标 |
| searchClearIconWidth | dimension | 上述图标的宽度 |
| searchClearIconHeight | dimension | 上述图标的高度 |
| searchStartIcon | reference | 输入框start位置的图标 |
| searchStartIconWidth | dimension | 上述图标的宽度 |
| searchStartIconHeight | dimension | 上述图标的高度 |
| searchBgShapeRadius | dimension | 整个输入框的shape背景圆角大小 |
| searchBgShapeSolidColor | color | shape背景填充色 |
| searchBgShapeStrokeWidth | dimension | 设置shape背景的边框线宽 |
| searchBgShapeStrokeColor | color | 设置shape背景的边框线的颜色 |


# ChameleonView

可显示不同色块的infobar,用于展示存储分类信息、简单数据汇总等场景

<img src="https://gitee.com/dayun220/private-img/raw/master/06.jpg" width="360" height="720" alt="ChameleonView"/><br/>

## 属性表格
| name | format | description |
| ------ | ------ | ------ |
| clShapeRadius | dimension | 底色背景的圆角大小 |
| clShapeSolidColor | color | 底色 |

## 使用示例代码

```
        // 设置颜色
        int[] colors = {
                Color.parseColor("#A833FF"),
                Color.parseColor("#FFD320"),
                Color.parseColor("#FF4B73")};
        // 设置颜色对应的value值
        float[] childValues = {40.4f, 10.56f, 20.44f};
        // 第三个参数是infobar的总值
        chameleon.setColors(colors, childValues, 100.00f);
        // 绑定生命周期
        chameleon.bindLifecycleObserver(this);
```

