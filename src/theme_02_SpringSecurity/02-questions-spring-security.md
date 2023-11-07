# Lecture Spring security

1. За какво се използва Spring Security?
    - за създаване на Authentication (кой работи със софтуера) и Authorization (какви права за софтуера има той) за
      проектите
    - това е стандарт за Spring приложенията, а също за Servlet stack и REACT stack
2. Как се инсталира Spring Security в servlet проект?
    - когато се използва в Servlet Project се инсталира като Servlet Filter и
    - по този начин успява да прихваща всички request-и, които идват към Dispatcher Servlet-a (който е Front-End
      Controller-a във Servlet приложението)
    - съответно Dispatcher Servlet-a дава отговор на въпроса дали даден request може да мине или не
3. Какво е Filter и за какво се използва?
    - той не част от Spring Приложението, а е по-скоро част от Servlet API и
    - прихваща (нещо като Interceptor) на всичките Request-и и Response-и, които пристигат в/връщат се от приложението;
    - с помоща на Filter-ите можем да прихващаме тези Request-и и да изпълняваме различни неща върху тях, напр:
    - Spring Security прави Authentication и Authorization
4. Каква е разликата между Filter и Interceptor?
    - Interceptor е нещо подобно на Filter, но
    - Filter- седи на входа преди Dispatcher Servlet-a, а
    - Interceptor-а седи медота на Controller-a, който изпълняваме (преди метода на Handler-a на Request-a),
    - защото в Spring не само Controller-ите могат да изпълняват някави Request-и
5. Как се хешират паролите на потребителите, когато ги съхраняваме в базата данни?
    - паролите, когато се съхраняват в базите даннни се хешират с помоща на някакъв криптографски хеш
    - идеята на тези хешове е да не съхраняваме паролата в някакъв възстановим вид,
    - а по скоро да сравняваме хеша на суровата парола, с която потребителят се логва със запазения в базата хеш
6. При form login - кои са трите been-a, които трябва да се създадът?
    - UserDetailsService - използва се, за да предаде нашата вътрешна репрезентация на User-ите (например UserEntity)
      към нещо, което Spring разбира (UserDetails)
    - SecurityFilterChain - с негова помощ настройваме т.нар. HttpSecurity, където указваме какви Request-и са разрешени
      за какви Request-и трябва Authorization, какви точно login механизми искаме да използваме, как искаме да се
      logout-ваме и др.
    -
7. Как управляваме достъпа до различните ресурси в приложението?
    - с помоща на настройките, които правим на HttpSecurity, като там имаме AuthorizeHttpRequestsConfigurer,
    - с AuthorizeHttpRequestsConfigurer може да се укаже кои ресурси са отворени за всички потребители и кои са свързани
      с някаква Authentication
8. Какво представлява `@PreAuthorize` анотацията?
    - тя също е един вид механизъм за Authorization,
    - като обикновенно  `@PreAuthorize` анотацията се слага върху Service-ите и Controller-ите
9. Каква e разликата между authentication и authorization?
    - Authentication - отговатя на въпроса кой работи със софтуера/системата
    - Authorization - отговатя на въпроса какви права има този човек в нашата система 