package pattern;
/*Desctription
1.Есть Клиент [ClientFilter]. Клиент выбирает себе Управляющего [FilterManager] и задает Запрос [sendRequest("HOME");]
Запрос [sendRequest("HOME");] - Это данные, которые должны пройти через фильтры и попасть к Цели [Target].
2.Управляющий через композицию (хотя это не обязательно, можно использовать агрегирование) выбирает Рабочего [FilterChain],
которому передает  Фильтры [Filter], которые он считает надо использовать для решения задачи и Цель [Target], к которой
должен прийти прошедший через фильтры и обработанный фильтрами Запрос [sendRequest("HOME");]
3.Рабочий [FilterChain], получив Фильтры [Filter] и Цель [Target], пропускает через Фильтры [Filter]
Ему всеровно, какие Фильтры [Filter] и какая Цель [Target] и какие данные Запрос [sendRequest("HOME");].
Он пропускает данные  Запрос [sendRequest("HOME");] через Фильтры [Filter] и передает Цель [Target].

 */

import java.util.ArrayList;
import java.util.List;
// https://www.tutorialspoint.com/design_pattern/intercepting_filter_pattern.htm
public class FilterExample {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        ClientFilter client = new ClientFilter();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }
}

interface Filter {  void execute(String request);    }

//  Create concrete filters. ----------------------------------------------------------------------
class AuthenticationFilter implements Filter {
    public void execute(String request){ System.out.println("Authenticating request: " + request);  request.replace("H","A H"); }
}

class DebugFilter implements Filter {
    public void execute(String request){ System.out.println("request log: " + request);  }
}

class Target {
    public void execute(String request){ System.out.println("Executing request: " + request); }
}

class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter){ filters.add(filter); }
    public void setTarget(Target target){   this.target = target;   }
    public void execute(String request){
        for (Filter filter : filters)   filter.execute(request);
        target.execute(request);
    }
}

class FilterManager {
    FilterChain filterChain;

    public FilterManager(Target target){ filterChain = new FilterChain(); filterChain.setTarget(target);  }
    public void setFilter(Filter filter){ filterChain.addFilter(filter);  }
    public void filterRequest(String request){ filterChain.execute(request); }
}

class ClientFilter {
    FilterManager filterManager;
    public void setFilterManager(FilterManager filterManager){  this.filterManager = filterManager; }
    public void sendRequest(String request){ filterManager.filterRequest(request);  }
}