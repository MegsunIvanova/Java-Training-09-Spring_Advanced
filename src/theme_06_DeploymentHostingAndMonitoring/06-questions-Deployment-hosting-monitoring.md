# Lecture - Deployment, Hosting and Monitoring

1) Какво представлява Spring actuator? 
    - Spring actuator е един starter, които то ни дава възможност да имплементираме т.нар non-functional requirements
    - напр. метрики за следене, статистики за been-ове и т.н.
    - 
2) Как може да се конфигурира Actuator - да се разрешават или забраняват определени endpoints?
    - най-лесно това може да стане с настроики в application.yml/.properties
    - management.endpoints...
    - 
3) На същия порт като приложението ли работи actuator?
    - да, но може да се смени с настройка в application.yml/.properties на друг порт
   
4) Какво представлява micrometer?
    - API които може да използваме за expose-ване на някакви методи

5) Как може да експортваме метрики с помоща на micrometer? Познавате ли подобни технологии?
    - трябва да вкараме някаква имплементация и се expose-ва с помоща на Spring Actuator