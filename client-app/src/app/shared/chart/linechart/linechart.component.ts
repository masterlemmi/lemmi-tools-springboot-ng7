import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'linechart',
  templateUrl: './linechart.component.html',
  styleUrls: ['./linechart.component.scss']
})
export class LinechartComponent implements OnInit {

  @Input('data')
  multi =  [{name:"BPI_VISA",series:[{name:"2019-01-01","value":"64890.72727272727"},{name:"2019-04-01","value":"59903.375"},{name:"2019-05-01","value":"59534.69565217391"},{name:"2019-03-01","value":"55421.230769230766"},{name:"2019-02-01","value":"57899.142857142855"}]},{name:"BPI_BANKARD",series:[{name:"2019-04-01","value":"61617.3125"},{name:"2019-01-01","value":"59158.444444444445"},{name:"2019-02-01","value":"58662.153846153844"},{name:"2019-05-01","value":"59069.94117647059"},{name:"2019-03-01","value":"65164.739130434784"}]},{name:"CAR_LOAN",series:[{name:"2019-05-01","value":"61558.92857142857"},{name:"2019-02-01","value":"59241.11111111111"},{name:"2019-01-01","value":"56551.72727272727"},{name:"2019-03-01","value":"59368.0"},{name:"2019-04-01","value":"61156.53333333333"}]}];


  view: any[] = [700, 400];

  // options for the chart
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Number';
  showYAxisLabel = true;
  yAxisLabel = 'Value';
  timeline = true;

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
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
