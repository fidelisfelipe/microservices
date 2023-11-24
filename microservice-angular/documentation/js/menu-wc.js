'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">microservice-angular documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-bd31492481ee62f3cd45fc05c80256217e9a85668683e1b2e41236e90a604423fc53011613da9f5d6422bcfadf02c46533e61718496a8a3d91275025f63d0103"' : 'data-bs-target="#xs-components-links-module-AppModule-bd31492481ee62f3cd45fc05c80256217e9a85668683e1b2e41236e90a604423fc53011613da9f5d6422bcfadf02c46533e61718496a8a3d91275025f63d0103"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-bd31492481ee62f3cd45fc05c80256217e9a85668683e1b2e41236e90a604423fc53011613da9f5d6422bcfadf02c46533e61718496a8a3d91275025f63d0103"' :
                                            'id="xs-components-links-module-AppModule-bd31492481ee62f3cd45fc05c80256217e9a85668683e1b2e41236e90a604423fc53011613da9f5d6422bcfadf02c46533e61718496a8a3d91275025f63d0103"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LogsReaderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LogsReaderComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MessagesComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MessagesComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/ConversionModule.html" data-type="entity-link" >ConversionModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' : 'data-bs-target="#xs-components-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' :
                                            'id="xs-components-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' }>
                                            <li class="link">
                                                <a href="components/ConversionAddComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionAddComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionAddFormComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionAddFormComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionAlertComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionAlertComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionEditComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionEditComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionExchangeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionExchangeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionListComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionMenuComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionMenuComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConversionSearchComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionSearchComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                        'data-bs-target="#injectables-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' : 'data-bs-target="#xs-injectables-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' :
                                        'id="xs-injectables-links-module-ConversionModule-a402a58695c3d6191fa1b65ded3cec7764456b7056a0a39da9a72850c27f9f223018b4456fd669f42e1e84a56ba457ac368632b7f952576fc0481eb8b897931f"' }>
                                        <li class="link">
                                            <a href="injectables/ConversionService.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConversionService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/ConversionRoutingModule.html" data-type="entity-link" >ConversionRoutingModule</a>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#classes-links"' :
                            'data-bs-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/Conversion.html" data-type="entity-link" >Conversion</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/ConversionService.html" data-type="entity-link" >ConversionService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/InMemoryDataService.html" data-type="entity-link" >InMemoryDataService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MessageService.html" data-type="entity-link" >MessageService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/RxStompService.html" data-type="entity-link" >RxStompService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/Exchange.html" data-type="entity-link" >Exchange</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/TypeConversion.html" data-type="entity-link" >TypeConversion</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/functions.html" data-type="entity-link">Functions</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});