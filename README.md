AService、AServiceImpl、BaseService、BaseBaseService之间的关系。
![image](https://github.com/user-attachments/assets/678218b4-261c-4371-be50-a908f3cb7253)

### 解决属性依赖的问题
- 先实例化 AService，此时它是早期的不完整毛胚实例，好多属性还没被赋值，将实例放置到 earlySingletonObjects 中备用。然后给 AService 注入属性，这个时候发现它还要依赖 BaseService。
- 实例化 BaseService，它也是早期的不完整毛胚实例，我们也将实例放到 earlySingletonObjects 中备用。然后再给 BaseService 注入属性，又发现它依赖 BaseBaseService。
- 实例化 BaseBaseService，此时它仍然是早期的不完整的实例，同样将实例放置到 earlySingletonObjects 中备用，然后再给 BaseBaseService 属性赋值，这个时候又发现它反过来还要依赖 AService。
- 我们从 earlySingletonObjects 结构中找到 AService 的早期毛胚实例，取出来给 BaseBaseService 注入属性，这意味着这时 BaseBaseService 所用的 AService 实例是那个早期的毛胚实例。这样就先创建好了 BaseBaseService。
- 程序控制流回到第二步，完成 BaseService 的属性注入。
- 程序控制流回到第一步，完成 AService 的属性注入。至此，所有的 Bean 就都创建完了。
通过上述过程可以知道，这一系列的 Bean 是纠缠在一起创建的，我们不能简单地先后独立创建它们，而是要作为一个整体来创建。

### 心得体会
第一二节分别演示了，beans.xml文件中，setter注入以及构造器注入的一些流程。但是如果有多个类，而且这些类之间具有一些关系，即一个Bean需要依赖另外一个Bean的时候，此时如何配置以及管理这些关系。

为啥要引用<ref>这个标签？

在Spring中，通常使用XML配置文件来进行定义和配置Bean。对于一些简单的属性的值的注入，可以直接使用<property>标签的value属性来进行值的指定（或者<constructor>的value属性），但是，如果属性是一个复杂的对象，就不能使用value来进行注入了，因为一个简单的值无法表示一个对象的全部状态。
为了解决这个问题，引入了<ref>属性（即reference的缩写），它可以用来引用已经在IOC容器定义的另外一个Bean。


当Spring发现两个或更多个bean之间存在循环依赖关系时，它会将其中一个bean创建的过程中尚未完成的实例放入earlySingletonObjects缓存中，然后将创建该bean的工厂对象放入singletonFactories缓存中。接着，Spring会暂停当前bean的创建过程，去创建它所依赖的bean。当依赖的bean创建完成后，Spring会将其放入singletonObjects缓存中，并使用它来完成当前bean的创建过程。在创建当前bean的过程中，如果发现它还依赖其他的bean，Spring会重复上述过程，直到所有bean的创建过程都完成为止。
需要注意的是，当使用构造函数注入方式时，循环依赖是无法解决的。因为在创建bean时，必须先创建它所依赖的bean实例，而构造函数注入方式需要在创建bean实例时就将依赖的bean实例传入构造函数中。如果依赖的bean实例尚未创建完成，就无法将其传入构造函数中，从而导致循环依赖无法解决。此时，可以考虑使用setter注入方式来解决循环依赖问题。
