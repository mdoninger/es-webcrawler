import {Component} from "angular2/core";
import {SearchService} from "./search.service";
import {SearchResult} from "./searchresult";

@Component({
    selector: 'search-app',
    templateUrl: 'search.html',
    providers: [SearchService]
})
export class SearchComponent {
    public searchResult:SearchResult[];
    public title = "Welcome to the Webcrawler Search";
    public searchInput:string;

    constructor(private _searchService:SearchService) {
    }

    submitQuery() {
        this._searchService.search(this.searchInput).then(result => this.searchResult = result);
    }

}