UML类图
![image](https://github.com/user-attachments/assets/b4f3c068-407c-41be-86d3-5817db527eec)

## IOC容器

它的意思就是使用Bean容器来管理一个个的Bean，最简单的bean就是java的业务对象。在Java之中，创建一个简单的对象的最简单的方法就是使用new关键字。IOC容器也就是BeanFactory，存在的意义**就是将创建对象与使用对象的业务代码进行解耦操作**，让业务开发人员无需关注底层的对象（bean）的构建和生命周期的管理，只是关注于业务开发。

作为一个bean它的实现可以是非常原始的，非常简单的，实际上我们只是需要几个简单的部件，==用一个部件来对应bean内存的映像，一个定义在外面的Bean在内存中总是需要有一个映像的，一个XML reader负责从外部的 XML文件获取bean的配置，也就是说这些bean是怎么声明的，我们可以写在一个外部的文件中，然后我们用XML reader 从外部文件中读取进来，我们还需要一个反射的部件，负责加载Bean Class并且来创建这个实例；创建实例之后，我们使用Map来保存Bean的实例，最后提供一个getBean（）的方法供给外部使用==，这个IOC容器就做好了。

## Q
1、ClassPathXmlResource类中的SAXReader是干啥的？ 
SAXReader saxReader = new SAXReader();
SAXReader是dom4j提供的一个类，专门用来读取与解析xml文件。
2、不理解为啥最后要向着singletons里面put？这个map存在的意义是什么？
在chatGPT中是这样回答的：
singleton是一个用于存储已经存在的单例对象的Map，其 key 是 beanName，value 是对应的单例对象。在 getBean 方法中，当你请求获取一个 bean 时，首先会检查 singletons 中是否已经存在这个 bean 的实例：
存在：如果 singletons 中已经存在对应的实例，直接返回该实例。这样就避免了重复创建相同的对象，确保了单例的特性。
不存在：如果 singletons 中不存在该实例，则会创建一个新的实例，并将其放入 singletons 中。这一步是为了确保下次请求同一个 bean 时，可以直接从 singletons 中获取，而不需要重新创建。
3、不理解为什么已经有了一个beanDefinitions列表，还仍旧需要beanNames这样一个表来进行维护？
在chatGPT中是这样回答的：
虽然 beanDefinition 列表保存了 bean 的详细定义信息，但是 beanNames 列表提供了一个名称索引机制，支持快速访问、唯一性检查、顺序控制、以及增强可读性和管理性等功能。这两者相辅相成，共同确保 BeanFactory 能够高效、正确地管理 bean。


-  readxml方法从资源文件读取内容并存入beanDefinitions，这件事情有两个地方不确定，资源的来源不同、资源的格式不同，抽象的Resource的接口，它的不同子类从不同的来源读取，但是最终都是以Resource接口的形式提供给外部访问的，这样解决了第一个不确定来源的问题；但是resource接口中被迭代的object又是根据不同格式不同而不同的，element只是xml格式的，所以又定义了BeanDefinitionReader接口，它的不同子类可以读取不同格式的资源来形成beanDefinition 。
-  instanceBeans方法取消了 。 
-  getBean方法功能增强了，不仅是获得bean，对于未创建的bean还要创建bean 。
-  新的applicationContext负责组装，可以根据它的名字来体现它的组装功能，例如ClassPathXmlApplicationContext  它组装的Resource的实现类是ClassPathXmlResource  ，然后因为是xml的，所以需要BeanDefinitionReader的实现类XmlBeanDefinitionReader来读取并注册进beanFactory，同时ApplicationContext也提供了getBean底层调用beanfactory的实现，提供了registerBeanDefinition  来向底层的beanFactory注册bean。
-  beanFactory 提供了registerBeanDefinition和getBean接口，这样无论是applicationContext还是beanDefinitionReader都可以向它注册beanDefinition，只要向它注册了，就可以调用它的getBean方法。

1, 反转: 反转的是对Bean的控制权, 使用"new"的方式是由程序员在代码中主动控制; 使用IOC的方式是由容器来主动控制Bean的创建以及后面的DI属性注入; 2, 反转在代码中的体现: 因为容器框架并不知道未来业务中需要注入哪个Bean, 于是通过配置文件等方式告诉容器, 容器使用反射技术管理Bean的创建, 属性注入, 生命周期等.



### 小结
通过本小节的学习，学习到了，ClassPathXmlApplicationContext，这个类调用ClassPathXmlResource解析了beans.xml文件（形成之后以Resource接口形式提供外部访问），创造了SimpleBeanFactroy对象，然后创建XMLBeanDefinitionReader的实例，并将SimpleBeanFactroy传递给它。
通过loadBeanDefinitions(resource)方法，XmlBeanDefinitionReader会从Xml文件中读取所有Bean的定义，并将这些定义注册到传递进来的 BeanFactory中（但是在其重写在SimpleBeanFactory中）。

获取Bean的实例，当调用getBean方法的时候，首先检查singletons中是否存在Bean的实例，如果存在的话，直接返回该实例。如果没有找到该实例SimpleBeanFactory 会查找 beanDefinitions 中是否有对应的定义。如果找到定义，就使用反射机制实例化这个 Bean，并将其存储到 singletons 中，以便下次直接使用。如果既没有找到实例也没有找到定义，会抛出 NoSuchBeanDefinitionException。

一旦获取到Aservice的实例，就会调用它的方法sayHello()。


此节课中进行注入方式为Setter方式进行注入，这种方式是指对象（或者bean）已经被创建之后，再将其依赖项注入到对象的一种方式。
