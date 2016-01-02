import {Injectable} from 'angular2/core';
//import {Http} from 'angular2/http';
import {RESULT} from './mock-result';

@Injectable()
export class SearchService {

    //constructor(private _http:Http) {
    //}

    search(searchQuery) {
        //return Promise.resolve(this._http.get("http://localhost:8080/search?q=" + searchQuery).map(res => res.json()));
        return Promise.resolve(RESULT);
    }
}