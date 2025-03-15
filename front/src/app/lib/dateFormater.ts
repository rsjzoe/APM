import dayjs from 'dayjs';
import 'dayjs/locale/fr'; // import locale

dayjs.locale('fr'); // use locale
export class DateFormater {
  static format(date: Date | string) {
    return dayjs(date).format('DD MMMM YYYY');
  }

  static getMonth(date: Date | string) {
    return dayjs(date).format('MMM');
  }
}
