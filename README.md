UML类图如下：
![image](https://github.com/user-attachments/assets/982525c7-73f2-4e1d-83da-696fafc9ffcf)
自己画的，用于基本梳理关系，但是不知道画的正确与否。
### 心得体会
本节也是看了很久很久，因为这一章节，类实在是太多了，但是总体梳理下来感觉就是对于上一节的内容实现了增强操作而已，其主要就是对于SimpleBeanFactroy进行了解放，以及增加了bean.xml文件的内容。

SimpleBeanFactory现在用于存储真正的bean对象（beanName, Object），它将上一节课中保存的singleton拆分了出来进行了管理，simpleBeanFactory仅仅是对于bean的一些管理操作(createBean、containsBean)。

按照顺序来说，也就是上一节最后一段而已，增加的一部分是，
1、在解析了XML文件并且将其转化为BeanDefinition的形式之后每次注册一个BeanDefinition的时候，如果不是懒加载就立刻调用了getBean方法（这个方法也是在SimpleBeanFactory中），而getBean()方法会从SimpleBeanFactory继承DefaultSingletonBeanRegistry的能力中判断bean是否存在，如果不存在，创建并且注册进DefaultSingletonBeanRegistry。
2、ClassPathXmlApplicationContext它的组装逻辑和上一节一样，但是现在XmlBeanDefinitionReader在遍历Resource向着simpleBeanFactory注册的时候，由于simplebeanFactory注册时候会创建所有非懒加载bean，所以现在applicationContext启动的时候就会创建所有非懒加载bean，上一节课中容器创建完成之后并不会创造bean,要到了获取bean的时候才会创建bean，对于getbean方法的调用提前到了注册beanDefinition的时候了。

不理解为啥会先输出abc, 3
具体执行顺序：
加载和解析 beans.xml：XmlBeanDefinitionReader 解析 beans.xml 文件，提取出 AServiceImpl 的构造参数 "abc" 和 3，并将这些参数存储在 ArgumentValues 对象中。
实例化 Bean：BeanFactory 调用 AServiceImpl 的构造方法，并传入 "abc" 和 3 作为参数。在构造函数中，输出了这两个参数的值。
完成实例化：构造方法执行完毕后，AServiceImpl 的实例就被创建出来了，随后可能还会进行依赖注入或其他初始化操作。

此节课程，将lazyInit赋值为false(!lazyInit为True)，然后getBean()，并且createbean()，进行了操作。这个应该属于**预加载**，在容器启动的时候就加载并且实例化所有的bean(所以，它先输出了abc,3)。
