## 图片加载类并做好了缓存实现
###使用方法如下:
```java
ImageLoader.with(this)
                .from(url)
                .cacheWith(DoubleCacheUtils.getInstance())
                .placeHolder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
```