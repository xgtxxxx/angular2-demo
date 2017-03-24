import {Component, Inject} from '@angular/core';
import { ActivatedRoute, Params } from "@angular/router";
@Component({
    selector: 'app-error',
    templateUrl: './app.error.component.html'
})
export class AppErrorComponent {
    private errorMessage:string;

    constructor(@Inject(ActivatedRoute) private route:ActivatedRoute) {
        let vm = this;
        this.route.params.subscribe(params => {
            this.errorMessage = params['message'] || "Unknown error!";
        });
    }
}
