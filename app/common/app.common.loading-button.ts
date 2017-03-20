import './app.common.loading-button.scss';
import {Component, Input, Output, EventEmitter} from '@angular/core';
@Component({
    selector: 'loading-button',
    templateUrl: './app.common.loading-button.html'
})
export class LoadingButton {
    constructor () {}
    @Input() name : string;
    @Input() isLoading : boolean;

    @Output() action = new EventEmitter();

    private apply = function(){
        this.action.emit();
    }
}
