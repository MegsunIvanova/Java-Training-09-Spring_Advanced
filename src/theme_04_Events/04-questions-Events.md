# Events in Spring

1) Кой е главния клас, който подсигурява Event Handling в Спринг?
    - ApplicationContext-а
   
2) Как се прави Custom Event?
    - следва да направим 3 неща: 
      -> създава се event class, които extend-ва ApplicationEvent, в неговия конструктор се подава Source-a на event-a
      -> създава се Publisher, които има метод publishEvent(), той хваща even-та и го изпраща на ApplicationContext-а
      -> добавя се event listener - добавя се метод в някой manage component (с анотация @Service, @Component, @Configuration), 
         които е анотиран с @EventListener, 
         в метод се inject-ва even-та които сме създали

3) Как може да обвържем Event s Transaction?
   - има специален listener - TransactionalEventListener, които се вкарва в контекста на трансакцията
   
4) Какво е Scheduling?
   - да се стратира някакъв task в background-a

5) Как може да имплементираме Scheduler в Spring?
   - да се enable support за Scheduling в Spring,като за целата се слага @EnableScheduling анотацията върху главния клас на Application-a
   - да анотираме с @Scheduled метода в manage компонент, които искаме да се изпълнява, като в него задаваме и времето/периода за изпълнение;
     -> с Cron - това е expression, с които да селектнем времената в бъдещето за изпълнение
     -> с Fixed Rate - през колко време да се изпълнява (не изчаква предходния task, ако не е приключил)
     -> с Fixed Delay - през колко време да се изпълнява (изчаква предходния task, ако не е приключил)

6) Каква е разликата между cron, fix rate и fix delay?
   - виж т.5 

7) Какво представлява Spring Cache?
   - това е Spring-Starter, които ни позволява да анотираме някакъв caching механизъм за данни, чийто достъп е чувсвителен спрямо performance
   - съдържа най-различни анотации за caching - за refresh, delete и т.н.

8) Какво е proxy?
   - proxy e class, които изглежда като даден class A например, 
   - като proxy-to прихваща извикванията към class A и прави нещо допълнително

9) Какви Proxy-та създава Spring?
   - с proxy работят конфигурациите, preauthorize анотациите, Spring Cache
   - Spring създава два вида proxy: dynamic JDK proxy, s

10) Може ли в рамките на един и същи Service от нетразакционен метод да извикаме тразакционен и ще работи ли това? Защо?
   - няма да работи, защото тези методи не минават през proxy-to, те се викат от вътре 