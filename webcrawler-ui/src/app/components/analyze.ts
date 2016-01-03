import {Component} from "angular2/core";
import {SearchService} from "../services/search.service";
import {Response} from "angular2/http";

@Component({
    selector: 'analyze',
    templateUrl: 'templates/analyze.html',
    providers: [SearchService]
})
export class AnalyzeComponent {
    public inputUrl:string;
    public analyzeState:number;

    constructor(private _searchService:SearchService) {
    }

    analyzeUrl() {
        this._searchService.analyze(this.inputUrl).subscribe((response:Response) => this.analyzeState = response.status);
    }
}