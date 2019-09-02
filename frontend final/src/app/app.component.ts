import { Component, OnInit } from '@angular/core';
import { Name } from './name';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  filename: string = '';


  constructor(){}

  ngOnInit() { }
  

  
}

