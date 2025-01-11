import { Component } from '@angular/core';
import { ChartOptions, ChartType, ChartDataset } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { timeColor } from './color';
import { Application } from '../../application-APM/appType';
import { applications } from '../../application-APM/data';

@Component({
  selector: 'app-life-cycle-time',
  imports: [BaseChartDirective],
  templateUrl: './life-cycle-time.component.html',
  styleUrl: './life-cycle-time.component.scss',
})
export class LifeCycleTimeComponent {
  public apps: Application[] = applications;
  
  public bubbleChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      x: {
        title: {
          display: true,
          text: 'Valeur Métier',
        },
      },
      y: {
        title: {
          display: true,
          text: 'Coûts',
        },
      },
    },
    onHover: (event, elements, chart) => {
      // console.log(event);
      // console.log(elements);
      // console.log(chart);
      const bubbleInfo = document.getElementById('bubble-info');
      if (event.native == null) {
        return;
      }
      if (bubbleInfo == null) {
        return;
      }

      const element = chart.getElementsAtEventForMode(
        event.native,
        'nearest',
        { intersect: true },
        false
      )[0];

      if (element) {
        const datasetIndex = element.datasetIndex;
        const index = element.index;
        const data = this.bubbleChartData[datasetIndex].data[index];
        // console.log(data);
        // console.log(this.bubbleDataMap.get(JSON.stringify(data)));
        const currentApp = this.bubbleDataMap.get(JSON.stringify(data));
        if (currentApp == undefined) return;

        document.getElementById('app-name')!.innerText = ` ${currentApp.name}`;
        document.getElementById('app-value')!.innerText = `${data.x}`;
        document.getElementById('app-cost')!.innerText = `${data.y}`;
        document.getElementById('app-type')!.innerText = `${currentApp.time}`;

        const screenWidth = window.innerWidth;
        const screenHeight = window.innerHeight;
        const bubbleInfoWidth = bubbleInfo.offsetWidth;
        const bubbleInfoHeight = bubbleInfo.offsetHeight;
        const appType = document.getElementById('app-type')!;

        let left = (event.native as any).offsetX + 15;
        let top = (event.native as any).offsetY - 30;

        if (left + 400 > screenWidth) {
          left = left - bubbleInfoWidth - 70;
        }
        console.log(left, screenWidth, bubbleInfoWidth);

        if (top + 300 > screenHeight) {
          top = top - bubbleInfoHeight - 10;
        }

        bubbleInfo.style.left = `${left}px`;
        bubbleInfo.style.top = `${top}px`;
        bubbleInfo.style.display = 'block';

        if (currentApp.time == 'tolerate') {
          appType.style.color = timeColor.tolerate;
        } else if (currentApp.time == 'migrate') {
          appType.style.color = timeColor.migrate;
        } else if (currentApp.time == 'invest') {
          appType.style.color = timeColor.invest;
        } else if (currentApp.time == 'eliminate') {
          appType.style.color = timeColor.eliminate;
        }
      } else {
        bubbleInfo.style.display = 'none';
      }
    },
  };

  public bubbleChartType: ChartType = 'bubble';
  public bubbleChartData: ChartDataset<'bubble'>[] = this.generateDataSet()

  generateDataSet() {
    let dataInvest = [];
    let dataTolerate = [];
    let dataMigrate = [];
    let dataEliminate = [];
    for (let app of this.apps) {
      const item = {
        x: app.businessValue,
        y: app.costBuild + app.costRun,
        r: app.userTotal,
      };

      if (app.time == 'invest') {
        dataInvest.push(item);
      } else if (app.time == 'migrate') {
        dataMigrate.push(item);
      } else if (app.time == 'tolerate') {
        dataTolerate.push(item);
      } else if (app.time == 'eliminate') {
        dataEliminate.push(item);
      }
    }
    const bubbleChartData: ChartDataset<'bubble'>[] = [
      {
        label: 'Tolerate',
        data: dataTolerate,
        backgroundColor: timeColor.tolerate,
      },
      {
        label: 'Invest',
        data: dataInvest,
        backgroundColor: timeColor.invest,
      },
      {
        label: 'Migrate',
        data: dataMigrate,
        backgroundColor: timeColor.migrate,
      },
      {
        label: 'Eliminate',
        data: dataEliminate,
        backgroundColor: timeColor.eliminate,
      },
    ];
    return bubbleChartData
  }


  private bubbleDataMap: Map<string, Application> = this.appsToMap();

  public appsToMap() {
    let rep2: Map<string, Application> = new Map();

    for (let element of this.apps) {
      rep2.set(
        JSON.stringify({
          x: element.businessValue,
          y: element.costBuild + element.costRun,
          r: element.userTotal,
        }),
        element
      );
    }
    return rep2;
  }
}
