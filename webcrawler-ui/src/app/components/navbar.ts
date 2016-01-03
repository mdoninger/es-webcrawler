import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router, Location} from 'angular2/router';

@Component({
    selector: 'navbar',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'navbar.html'
})
export class Navbar {

    constructor(public location: Location) {}

}