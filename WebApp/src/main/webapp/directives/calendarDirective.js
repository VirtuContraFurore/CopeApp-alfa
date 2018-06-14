myapp.directive('mdCoolCalendar', function() {
    var directive = {};

    directive.restrict = 'E'; /* restrict this directive to elements */
    directive.templateUrl = "directives/calendarDirective.html";

    return directive;
});