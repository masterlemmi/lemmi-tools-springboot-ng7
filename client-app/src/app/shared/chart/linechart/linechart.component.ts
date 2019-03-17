import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'linechart',
  templateUrl: './linechart.component.html',
  styleUrls: ['./linechart.component.scss']
})
export class LinechartComponent implements OnInit {

  @Input('data')
  multi =  [{name:"RCBC_VISA",series:[{name:"JANUARY 2018","value":"75777.31"},{name:"FEBRUARY 2018","value":"74213.94"},{name:"MARCH 2018","value":"74800.24"},{name:"APRIL 2018","value":"73939.06"},{name:"MAY 2018","value":"74589.24"},{name:"JUNE 2018","value":"72923.79"},{name:"JULY 2018","value":"73722.52"},{name:"AUGUST 2018","value":"73482.52"},{name:"SEPTEMBER 2018","value":"70848.14"},{name:"OCTOBER 2018","value":"73107.04"},{name:"NOVEMBER 2018","value":"74701.00"},{name:"DECEMBER 2018","value":"78277.35"},{name:"JANUARY 2019","value":"75793.16"},{name:"FEBRUARY 2019","value":"69975.68"},{name:"MARCH 2019","value":"75009.64"}]},{name:"CAR_LOAN",series:[{name:"JANUARY 2018","value":"74240.33"},{name:"FEBRUARY 2018","value":"74312.79"},{name:"MARCH 2018","value":"77442.41"},{name:"APRIL 2018","value":"78843.29"},{name:"MAY 2018","value":"73079.48"},{name:"JUNE 2018","value":"73531.28"},{name:"JULY 2018","value":"76899.68"},{name:"AUGUST 2018","value":"72172.04"},{name:"SEPTEMBER 2018","value":"76403.49"},{name:"OCTOBER 2018","value":"75184.58"},{name:"NOVEMBER 2018","value":"75248.33"},{name:"DECEMBER 2018","value":"79269.94"},{name:"JANUARY 2019","value":"74696.00"},{name:"FEBRUARY 2019","value":"74140.47"},{name:"MARCH 2019","value":"75593.79"}]},{name:"RCBC_BANKARD",series:[{name:"JANUARY 2018","value":"76483.63"},{name:"FEBRUARY 2018","value":"75040.10"},{name:"MARCH 2018","value":"77972.74"},{name:"APRIL 2018","value":"72128.31"},{name:"MAY 2018","value":"73964.00"},{name:"JUNE 2018","value":"76605.50"},{name:"JULY 2018","value":"78827.36"},{name:"AUGUST 2018","value":"74021.11"},{name:"SEPTEMBER 2018","value":"74674.49"},{name:"OCTOBER 2018","value":"74200.88"},{name:"NOVEMBER 2018","value":"76354.10"},{name:"DECEMBER 2018","value":"75889.98"},{name:"JANUARY 2019","value":"73919.49"},{name:"FEBRUARY 2019","value":"78718.39"},{name:"MARCH 2019","value":"74081.75"}]},{name:"BPI",series:[{name:"JANUARY 2018","value":"71233.03"},{name:"FEBRUARY 2018","value":"73758.86"},{name:"MARCH 2018","value":"75670.81"},{name:"APRIL 2018","value":"73080.65"},{name:"MAY 2018","value":"74872.88"},{name:"JUNE 2018","value":"78443.86"},{name:"JULY 2018","value":"78736.96"},{name:"AUGUST 2018","value":"72470.86"},{name:"SEPTEMBER 2018","value":"72510.52"},{name:"OCTOBER 2018","value":"79365.86"},{name:"NOVEMBER 2018","value":"75509.59"},{name:"DECEMBER 2018","value":"74962.95"},{name:"JANUARY 2019","value":"75821.24"},{name:"FEBRUARY 2019","value":"80972.04"},{name:"MARCH 2019","value":"77428.14"}]},{name:"BDO",series:[{name:"JANUARY 2018","value":"72071.68"},{name:"FEBRUARY 2018","value":"77247.93"},{name:"MARCH 2018","value":"73470.18"},{name:"APRIL 2018","value":"76177.00"},{name:"MAY 2018","value":"75409.43"},{name:"JUNE 2018","value":"74698.43"},{name:"JULY 2018","value":"76786.00"},{name:"AUGUST 2018","value":"75238.87"},{name:"SEPTEMBER 2018","value":"76150.33"},{name:"OCTOBER 2018","value":"75427.30"},{name:"NOVEMBER 2018","value":"72882.68"},{name:"DECEMBER 2018","value":"70633.17"},{name:"JANUARY 2019","value":"71446.16"},{name:"FEBRUARY 2019","value":"73628.78"},{name:"MARCH 2019","value":"75191.24"}]}];


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
