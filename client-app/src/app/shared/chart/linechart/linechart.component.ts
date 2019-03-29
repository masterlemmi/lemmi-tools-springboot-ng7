import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'linechart',
  templateUrl: './linechart.component.html',
  styleUrls: ['./linechart.component.scss']
})
export class LinechartComponent implements OnInit {

  
  multi =  []
  
 
  xAxisLabel = 'Number';

 
  yAxisLabel = 'Value';

  view: any[] = [700, 400];

  // options for the chart
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  showYAxisLabel = true;
  timeline = true;

  colorScheme = {
    domain: ['red', 'yellow', 'green', 'blue', 'violet']
  };

  // line, area
  autoScale = true;

  //pie
  showLabels = true;
  explodeSlices = false;
  doughnut = false;


  constructor() { }

  ngOnInit() {
  }

}
