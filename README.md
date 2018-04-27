# DelegateAdapters
Simplify creating recycler view adapters with many different view types.
This lib is inspired by Hannes Dorfmann [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

[Article](https://habr.com/post/341738/) about it on Russian.

## Dependencies

for java:
```groovy
compile 'com.github.liverm0r:delegateadapters:v1.11'
```

for kotlin:
```groovy
android {
    androidExtensions {
        experimental = true
    }
}
compile 'com.github.liverm0r:delegateadapters:v2.0'
```

You also have to add this in your project build.gradle

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

[![Build Status](https://travis-ci.org/sockeqwe/AdapterDelegates.svg?branch=master)](https://jitpack.io/#Liverm0r/delegateadapters)

## Example of usage in Kotlin

Write a model, which represents ui data:

```kotlin
class ImageViewModel(val title: String, @DrawableRes val imageRes: Int) : IComparableItem {

    override fun id(): Any = title
    override fun content(): Any = title + imageRes
}

```

ImageViewModel is just a POJO, implementing IComparableItem to be able to animate it out of the box with DiffUtils:
Write a delegate adapter for it:

```kotlin
class ImageDelegateAdapter(private val onImageClick: (ImageViewModel) -> Unit)
    : KDelegateAdapter<ImageViewModel>() {

    override fun onBind(item: ImageViewModel, viewHolder: KViewHolder)= with(viewHolder) {
        tv_title.text = item.title
        img_bg.setOnClickListener { onImageClick(item) }
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(items: List<*>, position: Int) = items[position] is ImageViewModel

    override fun getLayoutId(): Int = R.layout.image_item
}

```

Check part `with(viewHolder)`. This works like basic view holder without creating one. See the [View holder pattern support and caching options](
https://github.com/Kotlin/KEEP/blob/master/proposals/android-extensions-entity-caching.md
) for more information.

To enable this magic, you need to turn on kotlin ext. experimental feature in your module build.gradle file:

```groovy
androidExtensions {
    experimental = true
}
```

Just override onBind and onCreate methods.
Now you can use DiffUtilCompositeAdapter just like base RecyclerView.Adapter, composing it with whatever amount of delegate adapters:

```kotlin
        adapter = DiffUtilCompositeAdapter.Builder()
                .add(ImageDelegateAdapter(onImageClick))
                .add(TextDelegateAdapter())
                .add(CheckDelegateAdapter())
                .build()
```

![example](https://github.com/Liverm0r/DelegateAdapters/blob/master/feed_example.jpg)

## Example of usage in Java

Write a Model:

```java
public class TextViewModel implements IComparableItem {

    @NonNull public final String title;
    @NonNull public final String description;

    public TextViewModel(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    @Override public Object id() {return title;}
    @Override public Object content() {return title + description;}
}

```

Inherit from BaseDelegateAdapter:

```java
public class TextDelegateAdapter extends
    BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextViewModel> {

    @Override
    protected void onBindViewHolder(@NonNull View view,
                                    @NonNull TextViewModel item,
                                    @NonNull TextViewHolder viewHolder) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDescription.setText(item.description);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_item;
    }

    @NonNull
    @Override
    protected TextViewHolder createViewHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof TextViewModel;
    }

    final static class TextViewHolder extends BaseViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }
}

```

And create your CompositeDelegateAdapter (RecyclerView.Adapter):

```java
        adapter = new DiffUtilCompositeAdapter.Builder()
            .add(new ImageDelegateAdapter(onImageClick))
            .add(new TextDelegateAdapter())
            .add(new CheckDelegateAdapter())
            .build();
```

Check the examples in this project.

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
