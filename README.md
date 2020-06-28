# DelegateAdapters
Simplify creating recycler view adapters with many different view types.
This lib is inspired by Hannes Dorfmann [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

[Article](https://habr.com/post/341738/) about it on Russian.

## Dependencies

```groovy
android {
    //...
    androidExtensions {
        experimental = true
    }
}
implementation 'com.github.Liverm0r:delegateadapters:3.00'
```

You also have to add this in your project build.gradle

```groovy
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

[![Build Status](https://travis-ci.org/sockeqwe/AdapterDelegates.svg?branch=master)](https://jitpack.io/#Liverm0r/delegateadapters)

## Examples

Write a model, which represents ui data:

```kotlin
data class ImageItem(val title: String, @DrawableRes val imageRes: Int)
```

Write a delegate adapter:

```kotlin
class ImageDelegateAdapter(private val clickListener: View.OnClickListener) : KDelegateAdapter<ImageItem>() {

    override fun KViewHolder.onBind(item: ImageItem) {
        tv_title.text = item.title
        img_bg.setOnClickListener(clickListener)
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(item: Any) = item is ImageItem
    override fun getLayoutId(): Int = R.layout.image_item
    override fun ImageItem.getItemId(): Any = title // DiffUtils will use this to know that items are the same
}
```

Check `KViewHolder.onBind` part. This works like the basic view holder without creating one. Just override onBind method. See the [View holder pattern support and caching options](
https://github.com/Kotlin/KEEP/blob/master/proposals/android-extensions-entity-caching.md
) for more information.

To enable this magic, you need to turn on kotlin ext. experimental feature in your module build.gradle file:

```groovy
androidExtensions {
    experimental = true
}
```

Now you can use DiffUtilCompositeAdapter just like the base RecyclerView.Adapter, composing it with whatever amount of delegate adapters:

```kotlin
    val adapter = CompositeDelegateAdapter(
        TxtDelegateAdapter(),
        CheckDelegateAdapter(),
        GenerateItemsDelegateAdapter { generateNewData() }
    )
```

![example](https://github.com/Liverm0r/DelegateAdapters/blob/master/feed_example.jpg)

See example in code: [KotlinBaseExampleActivity.kt][1]

[1]: https://github.com/Liverm0r/DelegateAdapters/blob/master/example/src/main/java/com/example/dumchev/delegateadapters/base/BaseExampleActivity.kt


  ## License

```
Copyright 2017 Artur Dumchev 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
