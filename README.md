# springdemo-one
## FAQ: Why do we use CricketCoach class instead of Coach Interface?
### Question:

For the CricketCoach example with Setter Injection, why do we use the CricketCoach class instead of the Coach interface?

### Answer:

The getTeam() method is only defined in the CricketCoach class. It is not part of the Coach interface.

As a result, you would need the following code:

    CricketCoach theCricketCoach = context.getBean("myCricketCoach", CricketCoach.class); 

---

The Coach interface has two methods: getDailyWorkout and getDailyFortune

The CricketCoach class has four methods: getDailyWorkout, getDailyFortune, getTeam and setTeam

---

When you retrieve a bean from the Spring container using the Coach interface:

    Coach theCricketCoach = context.getBean("myCricketCoach", Coach.class); 

You only have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune. Even though the actual implementation has additional methods, you only have visibility to methods that are defined at the Coach interface level.

---

When you retrieve a bean from the Spring container using the CricketCoach class:

    CricketCoach theCricketCoach = context.getBean("myCricketCoach", CricketCoach.class); 

You have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune.

ALSO, you have access to the additional methods defined in the CricketCoach class: getTeam, setTeam.

---

The bottom line is it depends on how you retrieve the object and assign it ... that determines the visibility you have to the methods.

# Special Note about init and destroy Method Signatures

When using XML configuration, I want to provide additional details regarding the method signatures of the init-method  and destroy-method .

## Access modifier
The method can have any access modifier (public, protected, private)

## Return type
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

## Method name
The method can have any method name.

## Arguments
The method can not accept any arguments. The method should be no-arg.

# Special Note about Destroy Lifecycle and Prototype Scope
There is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the destroy method.  Gasp!



---

In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.



---

This also applies to both XML configuration and Annotation-based configuration.



---

QUESTION: How can I create code to call the destroy method on prototype scope beans



ANSWER:

You can destroy prototype beans but custom coding is required.

You can download the example source code from here: destroy-prototype-scope-bean-with-custom-processor-xml-config.zip



Development Process

1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans. The custom processor is configured in the spring config file.



	<!-- Bean custom processor to handle calling destroy methods on prototype scoped beans -->
        <bean id="customProcessor"
    		class="com.luv2code.springdemo.MyCustomBeanProcessor">
   	</bean>




2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destroy()" method.



public class TrackCoach implements Coach, DisposableBean {

	...
	
	// add a destroy method
	@Override
	public void destroy() throws Exception {
		System.out.println("TrackCoach: inside method doMyCleanupStuffYoYo");		
	}

}




3. The Spring configuration does not require use the destroy-method attribute. You can safely remove it.



 	<bean id="myCoach"
 		class="com.luv2code.springdemo.TrackCoach"
 		init-method="doMyStartupStuff"
 		scope="prototype">	
 		
 		<!-- set up constructor injection -->
 		<constructor-arg ref="myFortuneService" />
 	</bean>




4. In this app, BeanLifeCycleDemoApp.java is the main program.  TrackCoach.java is the prototype scoped bean. TrackCoach implements the DisposableBean interface and provides the destroy() method. The custom bean processing is handled in the MyCustomBeanProcessor class.

