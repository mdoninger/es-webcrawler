import {Injectable} from 'angular2/core';
import {Http, RequestOptions, URLSearchParams} from 'angular2/http';

@Injectable()
export class SearchService {

    constructor(private _http:Http) {
    }

    search(searchQuery) {
        //return Promise.resolve(this._http.get("http://localhost:8080/search?q=" + searchQuery).map(res => res.json()));

        const params = new URLSearchParams();
        params.set("q", searchQuery);

        return this._http.get("http://localhost:8080/search", new RequestOptions({search: params}));
    }

    analyze(url) {
        const params = new URLSearchParams();
        params.set("url", encodeURIComponent(url));

        return this._http.get("http://localhost:8080/import", new RequestOptions({search: params}));
    }
}