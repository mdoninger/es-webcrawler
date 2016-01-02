import {Component} from "angular2/core";
import {SearchService} from "./search.service";
import {SearchResult} from "./searchresult";
import {Response} from "angular2/http";

@Component({
    selector: 'search-app',
    templateUrl: 'search.html',
    providers: [SearchService]
})
export class SearchComponent {
    public searchResult:SearchResult[];
    public searchResultString:string;
    public title = "Welcome to the Webcrawler Search";
    public searchInput:string;
    public error:string;

    constructor(private _searchService:SearchService) {
    }

    submitQuery() {
        //this._searchService.search(this.searchInput).then(result => this.searchResult = result);
        this._searchService.search(this.searchInput).subscribe((response:Response) => this.searchResult = response.json()["content"]);
    }

}