# DelegateAdapters
Simplify the creation of `RecyclerView` adapters with different view types with this library, which is inspired by Hannes Dorfmann's [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

There is also an [article](https://habr.com/post/341738/) about it in Russian on Habr.

## Dependencies

```groovy
android {
    //...
    viewBinding { enabled = true }
}
dependencies {
    implementation 'com.github.Liverm0r:delegateadapters:4.0'
}
```

You will also need to add this to your project's `build.gradle` file: 

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

Write a model that represents UI data:

```kotlin
data class ImageItem(val title: String, @DrawableRes val imageRes: Int)
```

Write a delegate adapter:

```kotlin
class ImageDelegateAdapter(private val clickListener: View.OnClickListener) :
    ViewBindingDelegateAdapter<ImageItem, ImageItemBinding>(ImageItemBinding::inflate) {

    override fun ImageItemBinding.onBind(item: ImageItem) {
        tvTitle.text = item.title
        imgBg.setOnClickListener(clickListener)
        imgBg.setImageResource(item.imageRes)
    }

    override fun isForViewType(item: Any): Boolean = item is ImageItem

    override fun ImageItem.getItemId(): Any = title
}
```

Check out the `ImageItemBinding.onBind` part. This works like a basic view holder without requiring you to create one. Simply override the `onBind` method. Note that for this to work, you need to turn on view binding.

```groovy
viewBinding { enabled = true }
```

Now you can use the `DiffUtilCompositeAdapter` just like the base `RecyclerView.Adapter`, composing it with any number of delegate adapters:

```kotlin
    val adapter = CompositeDelegateAdapter(
        TxtDelegateAdapter(),
        CheckDelegateAdapter(),
        GenerateItemsDelegateAdapter { generateNewData() }
    )
```

![example](https://github.com/Liverm0r/DelegateAdapters/blob/master/feed_example.jpg)

See an example in the code: [BaseExampleActivity.kt][1]:

[1]: https://github.com/Liverm0r/DelegateAdapters/blob/master/example/src/main/java/com/livermor/dumchev/delegateadapters/base/BaseExampleActivity.kt


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
