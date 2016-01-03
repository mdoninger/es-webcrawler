import {Component} from "angular2/core";
import {ROUTER_DIRECTIVES, RouteConfig, Route} from 'angular2/router';
import {Navbar} from "./navbar";
import {SearchComponent} from "./search.component";
import {AnalyzeComponent} from "./analyze";

@Component({
    selector: 'app',
    directives: [ROUTER_DIRECTIVES, Navbar],
    template: `
        <navbar></navbar>
        <div class="container">
            <router-outlet></router-outlet>
        </div>
    `,
    styles: [`
    .container {
      padding-top: 70px;
    }
  `]
})
@RouteConfig([
    new Route({path: '/analyze', component: AnalyzeComponent, name: 'Analyze'}),
    new Route({path: '/', component: SearchComponent, name: 'Search'})
])
export class AppComponent {

}