# Lecture Error Handling

1. Какво представлява Whitelabel Error Page?
    - Whitelabel по прицип означава нещо, което е произведено, за да може да се модифицира от някой друг
    - Такъв е случая на Whitelabel Error Page на SpringBoot -
    - тя е един default-ен Error Page, който се вижда, може да настрои, скрие или Override-не
    - тя скрива Error Page-а на Server Container-a (в случая на TomCat)
2. Как може да я заменим?
   - по много начини: можем да я изключим с настройка в application.yml или
   - да я заменим като сложим error.html в папката templates
   - може също да се направи папка error в templates и в нея да има страници с иментата на http status code-s:
   - напр. 404.html, 500.html -> така при хвъляне на грешка ще се визуализира съответната страница
3. Как може да направим грешка даден HTTP status code да се визуализира в определен thymeleaf template?
   - Като сложим кода на името на html-a (виж т. 2) - 404.html например в папка error
4. Как може да обработваме Exception-ни в рамките на един Controller?
   - със съответната анотация вътре в Controller-a, 
   - където слагаме подходящо анотиран метод, в който слагаме нашия код, който обработава грешката
5. Как може да обработваме глобални Exception-ни?
   - с ExceptionHandler в клас, който е анотиран с @ControllerAdvice
6. Как може да върнем специфичен JSON при грешка в REST API?
   - с глобален или локален ExceptionHandler метод, с които следва да върнем ResponseBody с някакъв обект