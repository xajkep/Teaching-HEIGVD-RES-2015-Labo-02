


<!DOCTYPE html>
<html lang="en" class="">
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    
    
    <title>Teaching-HEIGVD-RES-2015-Labo-02/RouletteV2JPBWTest.java at fb-lab02-tests · benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02 · GitHub</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png">
    <meta property="fb:app_id" content="1401488693436528">

      <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" name="twitter:title" /><meta content="Teaching-HEIGVD-RES-2015-Labo-02 - Repo for lab 02 (TCP programming)" name="twitter:description" /><meta content="https://avatars3.githubusercontent.com/u/11058283?v=3&amp;s=400" name="twitter:image:src" />
      <meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://avatars3.githubusercontent.com/u/11058283?v=3&amp;s=400" property="og:image" /><meta content="benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" property="og:title" /><meta content="https://github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" property="og:url" /><meta content="Teaching-HEIGVD-RES-2015-Labo-02 - Repo for lab 02 (TCP programming)" property="og:description" />
      <meta name="browser-stats-url" content="/_stats">
    <link rel="assets" href="https://assets-cdn.github.com/">
    
    <meta name="pjax-timeout" content="1000">
    

    <meta name="msapplication-TileImage" content="/windows-tile.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="selected-link" value="repo_source" data-pjax-transient>
      <meta name="google-analytics" content="UA-3769691-2">

    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="collector-cdn.github.com" name="octolytics-script-host" /><meta content="github" name="octolytics-app-id" /><meta content="C186D802:79F1:35E3958:551B9323" name="octolytics-dimension-request_id" />
    
    <meta content="Rails, view, blob#show" name="analytics-event" />
    <meta class="js-ga-set" name="dimension1" content="Logged Out">
    <meta class="js-ga-set" name="dimension2" content="Header v3">
    <meta name="is-dotcom" content="true">
    <meta name="hostname" content="github.com">

    
    <link rel="icon" type="image/x-icon" href="https://assets-cdn.github.com/favicon.ico">


    <meta content="authenticity_token" name="csrf-param" />
<meta content="ZCNauL1VISZGJRv2qdiCi3q/UzQDJXvnil2ZyCZgesKsqcJ7RtTQDW16sFugbitZSDHGcKX0F64VEYoZWYC9oA==" name="csrf-token" />

    <link href="https://assets-cdn.github.com/assets/github-99ded28f32c4f6e88f302c0734a24bb4e36c00b4f26d125e038ee30b1f9432ae.css" media="all" rel="stylesheet" />
    <link href="https://assets-cdn.github.com/assets/github2-14b629a12b237ffde72fb70f31de6a515754f0f686189a072cc00e54ee202fd1.css" media="all" rel="stylesheet" />
    
    


    <meta http-equiv="x-pjax-version" content="48fe7616b94e76d06e6ff2100cf42123">

      
  <meta name="description" content="Teaching-HEIGVD-RES-2015-Labo-02 - Repo for lab 02 (TCP programming)">
  <meta name="go-import" content="github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02 git https://github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02.git">

  <meta content="11058283" name="octolytics-dimension-user_id" /><meta content="benoistwolleb" name="octolytics-dimension-user_login" /><meta content="32851976" name="octolytics-dimension-repository_id" /><meta content="benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="true" name="octolytics-dimension-repository_is_fork" /><meta content="32456305" name="octolytics-dimension-repository_parent_id" /><meta content="SoftEng-HEIGVD/Teaching-HEIGVD-RES-2015-Labo-02" name="octolytics-dimension-repository_parent_nwo" /><meta content="32456305" name="octolytics-dimension-repository_network_root_id" /><meta content="SoftEng-HEIGVD/Teaching-HEIGVD-RES-2015-Labo-02" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/commits/fb-lab02-tests.atom" rel="alternate" title="Recent Commits to Teaching-HEIGVD-RES-2015-Labo-02:fb-lab02-tests" type="application/atom+xml">

  </head>


  <body class="logged_out  env-production  vis-public fork page-blob">
    <a href="#start-of-content" tabindex="1" class="accessibility-aid js-skip-to-content">Skip to content</a>
    <div class="wrapper">
      
      
      


        
        <div class="header header-logged-out" role="banner">
  <div class="container clearfix">

    <a class="header-logo-wordmark" href="https://github.com/" data-ga-click="(Logged out) Header, go to homepage, icon:logo-wordmark">
      <span class="mega-octicon octicon-logo-github"></span>
    </a>

    <div class="header-actions" role="navigation">
        <a class="btn btn-primary" href="/join" data-ga-click="(Logged out) Header, clicked Sign up, text:sign-up">Sign up</a>
      <a class="btn" href="/login?return_to=%2Fbenoistwolleb%2FTeaching-HEIGVD-RES-2015-Labo-02%2Fblob%2Ffb-lab02-tests%2FQuizRouletteServer-build%2FQuizRouletteServer-test%2Fsrc%2Ftest%2Fjava%2Fch%2Fheigvd%2Fres%2Flabs%2Froulette%2Fnet%2Fclient%2FRouletteV2JPBWTest.java" data-ga-click="(Logged out) Header, clicked Sign in, text:sign-in">Sign in</a>
    </div>

    <div class="site-search repo-scope js-site-search" role="search">
      <form accept-charset="UTF-8" action="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/search" class="js-site-search-form" data-global-search-url="/search" data-repo-search-url="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/search" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
  <input type="text"
    class="js-site-search-field is-clearable"
    data-hotkey="s"
    name="q"
    placeholder="Search"
    data-global-scope-placeholder="Search GitHub"
    data-repo-scope-placeholder="Search"
    tabindex="1"
    autocapitalize="off">
  <div class="scope-badge">This repository</div>
</form>
    </div>

      <ul class="header-nav left" role="navigation">
          <li class="header-nav-item">
            <a class="header-nav-link" href="/explore" data-ga-click="(Logged out) Header, go to explore, text:explore">Explore</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="/features" data-ga-click="(Logged out) Header, go to features, text:features">Features</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="https://enterprise.github.com/" data-ga-click="(Logged out) Header, go to enterprise, text:enterprise">Enterprise</a>
          </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="/blog" data-ga-click="(Logged out) Header, go to blog, text:blog">Blog</a>
          </li>
      </ul>

  </div>
</div>



      <div id="start-of-content" class="accessibility-aid"></div>
          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    <div id="js-flash-container">
      
    </div>
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">
        
<ul class="pagehead-actions">

  <li>
      <a href="/login?return_to=%2Fbenoistwolleb%2FTeaching-HEIGVD-RES-2015-Labo-02"
    class="btn btn-sm btn-with-count tooltipped tooltipped-n"
    aria-label="You must be signed in to watch a repository" rel="nofollow">
    <span class="octicon octicon-eye"></span>
    Watch
  </a>
  <a class="social-count" href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/watchers">
    2
  </a>

  </li>

  <li>
      <a href="/login?return_to=%2Fbenoistwolleb%2FTeaching-HEIGVD-RES-2015-Labo-02"
    class="btn btn-sm btn-with-count tooltipped tooltipped-n"
    aria-label="You must be signed in to star a repository" rel="nofollow">
    <span class="octicon octicon-star"></span>
    Star
  </a>

    <a class="social-count js-social-count" href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/stargazers">
      0
    </a>

  </li>

    <li>
      <a href="/login?return_to=%2Fbenoistwolleb%2FTeaching-HEIGVD-RES-2015-Labo-02"
        class="btn btn-sm btn-with-count tooltipped tooltipped-n"
        aria-label="You must be signed in to fork a repository" rel="nofollow">
        <span class="octicon octicon-repo-forked"></span>
        Fork
      </a>
      <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/network" class="social-count">
        49
      </a>
    </li>
</ul>

        <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
          <span class="mega-octicon octicon-repo-forked"></span>
          <span class="author"><a href="/benoistwolleb" class="url fn" itemprop="url" rel="author"><span itemprop="title">benoistwolleb</span></a></span><!--
       --><span class="path-divider">/</span><!--
       --><strong><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" class="js-current-repository" data-pjax="#js-repo-pjax-container">Teaching-HEIGVD-RES-2015-Labo-02</a></strong>

          <span class="page-context-loader">
            <img alt="" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
          </span>

            <span class="fork-flag">
              <span class="text">forked from <a href="/SoftEng-HEIGVD/Teaching-HEIGVD-RES-2015-Labo-02">SoftEng-HEIGVD/Teaching-HEIGVD-RES-2015-Labo-02</a></span>
            </span>
        </h1>
      </div><!-- /.container -->
    </div><!-- /.repohead -->

    <div class="container">
      <div class="repository-with-sidebar repo-container new-discussion-timeline  ">
        <div class="repository-sidebar clearfix">
            
<nav class="sunken-menu repo-nav js-repo-nav js-sidenav-container-pjax js-octicon-loaders"
     role="navigation"
     data-pjax="#js-repo-pjax-container"
     data-issue-count-url="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/issues/counts">
  <ul class="sunken-menu-group">
    <li class="tooltipped tooltipped-w" aria-label="Code">
      <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests" aria-label="Code" class="selected js-selected-navigation-item sunken-menu-item" data-hotkey="g c" data-selected-links="repo_source repo_downloads repo_commits repo_releases repo_tags repo_branches /benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests">
        <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>


    <li class="tooltipped tooltipped-w" aria-label="Pull requests">
      <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/pulls" aria-label="Pull requests" class="js-selected-navigation-item sunken-menu-item" data-hotkey="g p" data-selected-links="repo_pulls /benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/pulls">
          <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull requests</span>
          <span class="js-pull-replace-counter"></span>
          <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>

  </ul>
  <div class="sunken-menu-separator"></div>
  <ul class="sunken-menu-group">

    <li class="tooltipped tooltipped-w" aria-label="Pulse">
      <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/pulse" aria-label="Pulse" class="js-selected-navigation-item sunken-menu-item" data-selected-links="pulse /benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/pulse">
        <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>

    <li class="tooltipped tooltipped-w" aria-label="Graphs">
      <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/graphs" aria-label="Graphs" class="js-selected-navigation-item sunken-menu-item" data-selected-links="repo_graphs repo_contributors /benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/graphs">
        <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
        <img alt="" class="mini-loader" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-e513294efa576953719e4e2de888dd9cf929b7d62ed8d05f25e731d02452ab6c.gif" width="16" />
</a>    </li>
  </ul>


</nav>

              <div class="only-with-full-nav">
                  
<div class="clone-url open"
  data-protocol-type="http"
  data-url="/users/set_protocol?protocol_selector=http&amp;protocol_type=clone">
  <h3><span class="text-emphasized">HTTPS</span> clone URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02.git" readonly="readonly">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>

  
<div class="clone-url "
  data-protocol-type="subversion"
  data-url="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone">
  <h3><span class="text-emphasized">Subversion</span> checkout URL</h3>
  <div class="input-group js-zeroclipboard-container">
    <input type="text" class="input-mini input-monospace js-url-field js-zeroclipboard-target"
           value="https://github.com/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02" readonly="readonly">
    <span class="input-group-button">
      <button aria-label="Copy to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
    </span>
  </div>
</div>



<p class="clone-options">You can clone with
  <a href="#" class="js-clone-selector" data-protocol="http">HTTPS</a> or <a href="#" class="js-clone-selector" data-protocol="subversion">Subversion</a>.
  <a href="https://help.github.com/articles/which-remote-url-should-i-use" class="help tooltipped tooltipped-n" aria-label="Get help on which URL is right for you.">
    <span class="octicon octicon-question"></span>
  </a>
</p>



                <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/archive/fb-lab02-tests.zip"
                   class="btn btn-sm sidebar-button"
                   aria-label="Download the contents of benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02 as a zip file"
                   title="Download the contents of benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02 as a zip file"
                   rel="nofollow">
                  <span class="octicon octicon-cloud-download"></span>
                  Download ZIP
                </a>
              </div>
        </div><!-- /.repository-sidebar -->

        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>
          

<a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/blob/0864de84880158c0b0042e1389b461b54fc1876c/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java" class="hidden js-permalink-shortcut" data-hotkey="y">Permalink</a>

<!-- blob contrib key: blob_contributors:v21:90039efdbe587e965a8e5dd62e0d5c21 -->

<div class="file-navigation js-zeroclipboard-container">
  
<div class="select-menu js-menu-container js-select-menu left">
  <span class="btn btn-sm select-menu-button js-menu-target css-truncate" data-hotkey="w"
    data-master-branch="master"
    data-ref="fb-lab02-tests"
    title="fb-lab02-tests"
    role="button" aria-label="Switch branches or tags" tabindex="0" aria-haspopup="true">
    <span class="octicon octicon-git-branch"></span>
    <i>branch:</i>
    <span class="js-select-button css-truncate-target">fb-lab02-tests</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax aria-hidden="true">

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-x js-menu-close" role="button" aria-label="Close"></span>
      </div>

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" data-filter-placeholder="Filter branches/tags" class="js-select-menu-tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" data-filter-placeholder="Find a tag…" class="js-select-menu-tab">Tags</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <a class="select-menu-item js-navigation-item js-navigation-open selected"
               href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/blob/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java"
               data-name="fb-lab02-tests"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="fb-lab02-tests">
                fb-lab02-tests
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/blob/master/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java"
               data-name="master"
               data-skip-pjax="true"
               rel="nofollow">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <span class="select-menu-item-text css-truncate-target" title="master">
                master
              </span>
            </a>
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div>

    </div>
  </div>
</div>

  <div class="btn-group right">
    <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/find/fb-lab02-tests"
          class="js-show-file-finder btn btn-sm empty-icon tooltipped tooltipped-s"
          data-pjax
          data-hotkey="t"
          aria-label="Quickly jump between files">
      <span class="octicon octicon-list-unordered"></span>
    </a>
    <button aria-label="Copy file path to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button" data-copied-hint="Copied!" type="button"><span class="octicon octicon-clippy"></span></button>
  </div>

  <div class="breadcrumb js-zeroclipboard-target">
    <span class='repo-root js-repo-root'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">Teaching-HEIGVD-RES-2015-Labo-02</span></a></span></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">QuizRouletteServer-build</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">QuizRouletteServer-test</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">src</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">test</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">java</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">ch</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">heigvd</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">res</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">labs</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">roulette</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">net</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/tree/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client" class="" data-branch="fb-lab02-tests" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">client</span></a></span><span class="separator">/</span><strong class="final-path">RouletteV2JPBWTest.java</strong>
  </div>
</div>

<include-fragment class="commit commit-loader file-history-tease" src="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/contributors/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java">
  <div class="file-history-tease-header">
    Fetching contributors&hellip;
  </div>

  <div class="participation">
    <p class="loader-loading"><img alt="" height="16" src="https://assets-cdn.github.com/assets/spinners/octocat-spinner-32-EAF2F5-0bdc57d34b85c4a4de9d0d1db10cd70e8a95f33ff4f46c5a8c48b4bf4e5a9abe.gif" width="16" /></p>
    <p class="loader-error">Cannot retrieve contributors at this time</p>
  </div>
</include-fragment>
<div class="file">
  <div class="file-header">
    <div class="file-actions">

      <div class="btn-group">
        <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/raw/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java" class="btn btn-sm " id="raw-url">Raw</a>
          <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/blame/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java" class="btn btn-sm js-update-url-with-hash">Blame</a>
        <a href="/benoistwolleb/Teaching-HEIGVD-RES-2015-Labo-02/commits/fb-lab02-tests/QuizRouletteServer-build/QuizRouletteServer-test/src/test/java/ch/heigvd/res/labs/roulette/net/client/RouletteV2JPBWTest.java" class="btn btn-sm " rel="nofollow">History</a>
      </div>


          <button type="button" class="octicon-btn disabled tooltipped tooltipped-n" aria-label="You must be signed in to make or propose changes">
            <span class="octicon octicon-pencil"></span>
          </button>

        <button type="button" class="octicon-btn octicon-btn-danger disabled tooltipped tooltipped-n" aria-label="You must be signed in to make or propose changes">
          <span class="octicon octicon-trashcan"></span>
        </button>
    </div>

    <div class="file-info">
        108 lines (98 sloc)
        <span class="file-info-divider"></span>
      4.359 kb
    </div>
  </div>
  
  <div class="blob-wrapper data type-java">
      <table class="highlight tab-size-8 js-file-line-container">
      <tr>
        <td id="L1" class="blob-num js-line-number" data-line-number="1"></td>
        <td id="LC1" class="blob-code js-file-line"><span class="pl-k">package</span> <span class="pl-smi">ch.heigvd.res.labs.roulette.net.client</span>;</td>
      </tr>
      <tr>
        <td id="L2" class="blob-num js-line-number" data-line-number="2"></td>
        <td id="LC2" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L3" class="blob-num js-line-number" data-line-number="3"></td>
        <td id="LC3" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol</span>;</td>
      </tr>
      <tr>
        <td id="L4" class="blob-num js-line-number" data-line-number="4"></td>
        <td id="LC4" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">org.junit.Rule</span>;</td>
      </tr>
      <tr>
        <td id="L5" class="blob-num js-line-number" data-line-number="5"></td>
        <td id="LC5" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">org.junit.rules.ExpectedException</span>;</td>
      </tr>
      <tr>
        <td id="L6" class="blob-num js-line-number" data-line-number="6"></td>
        <td id="LC6" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">ch.heigvd.res.labs.roulette.data.EmptyStoreException</span>;</td>
      </tr>
      <tr>
        <td id="L7" class="blob-num js-line-number" data-line-number="7"></td>
        <td id="LC7" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol</span>;</td>
      </tr>
      <tr>
        <td id="L8" class="blob-num js-line-number" data-line-number="8"></td>
        <td id="LC8" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">ch.heigvd.schoolpulse.TestAuthor</span>;</td>
      </tr>
      <tr>
        <td id="L9" class="blob-num js-line-number" data-line-number="9"></td>
        <td id="LC9" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">java.io.IOException</span>;</td>
      </tr>
      <tr>
        <td id="L10" class="blob-num js-line-number" data-line-number="10"></td>
        <td id="LC10" class="blob-code js-file-line"><span class="pl-k">import</span> <span class="pl-smi">org.junit.Test</span>;</td>
      </tr>
      <tr>
        <td id="L11" class="blob-num js-line-number" data-line-number="11"></td>
        <td id="LC11" class="blob-code js-file-line"><span class="pl-k">import static</span> <span class="pl-smi">org.junit.Assert.*</span>;</td>
      </tr>
      <tr>
        <td id="L12" class="blob-num js-line-number" data-line-number="12"></td>
        <td id="LC12" class="blob-code js-file-line"><span class="pl-c">/**</span></td>
      </tr>
      <tr>
        <td id="L13" class="blob-num js-line-number" data-line-number="13"></td>
        <td id="LC13" class="blob-code js-file-line"><span class="pl-c"> * This class contains automated tests to validate the client and the server</span></td>
      </tr>
      <tr>
        <td id="L14" class="blob-num js-line-number" data-line-number="14"></td>
        <td id="LC14" class="blob-code js-file-line"><span class="pl-c"> * implementation of the Roulette Protocol (version 1)</span></td>
      </tr>
      <tr>
        <td id="L15" class="blob-num js-line-number" data-line-number="15"></td>
        <td id="LC15" class="blob-code js-file-line"><span class="pl-c"> * <span class="pl-k">@author</span> Jan Purro, Benoist Wolleb</span></td>
      </tr>
      <tr>
        <td id="L16" class="blob-num js-line-number" data-line-number="16"></td>
        <td id="LC16" class="blob-code js-file-line"><span class="pl-c"> */</span></td>
      </tr>
      <tr>
        <td id="L17" class="blob-num js-line-number" data-line-number="17"></td>
        <td id="LC17" class="blob-code js-file-line"><span class="pl-k">public</span> <span class="pl-k">class</span> <span class="pl-en">RouletteV2JPBWTest</span> {</td>
      </tr>
      <tr>
        <td id="L18" class="blob-num js-line-number" data-line-number="18"></td>
        <td id="LC18" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L19" class="blob-num js-line-number" data-line-number="19"></td>
        <td id="LC19" class="blob-code js-file-line">    <span class="pl-k">@Rule</span></td>
      </tr>
      <tr>
        <td id="L20" class="blob-num js-line-number" data-line-number="20"></td>
        <td id="LC20" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-smi">ExpectedException</span> exception <span class="pl-k">=</span> <span class="pl-smi">ExpectedException</span><span class="pl-k">.</span>none();</td>
      </tr>
      <tr>
        <td id="L21" class="blob-num js-line-number" data-line-number="21"></td>
        <td id="LC21" class="blob-code js-file-line">
</td>
      </tr>
      <tr>
        <td id="L22" class="blob-num js-line-number" data-line-number="22"></td>
        <td id="LC22" class="blob-code js-file-line">    <span class="pl-k">@Rule</span></td>
      </tr>
      <tr>
        <td id="L23" class="blob-num js-line-number" data-line-number="23"></td>
        <td id="LC23" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-smi">EphemeralClientServerPair</span> roulettePair <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">EphemeralClientServerPair</span>(<span class="pl-smi">RouletteV2Protocol</span><span class="pl-c1"><span class="pl-k">.</span>VERSION</span>);</td>
      </tr>
      <tr>
        <td id="L24" class="blob-num js-line-number" data-line-number="24"></td>
        <td id="LC24" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L25" class="blob-num js-line-number" data-line-number="25"></td>
        <td id="LC25" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L26" class="blob-num js-line-number" data-line-number="26"></td>
        <td id="LC26" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L27" class="blob-num js-line-number" data-line-number="27"></td>
        <td id="LC27" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">theDataStoreShouldBeEmptyAtFirst</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L28" class="blob-num js-line-number" data-line-number="28"></td>
        <td id="LC28" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L29" class="blob-num js-line-number" data-line-number="29"></td>
        <td id="LC29" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L30" class="blob-num js-line-number" data-line-number="30"></td>
        <td id="LC30" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L31" class="blob-num js-line-number" data-line-number="31"></td>
        <td id="LC31" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">0</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L32" class="blob-num js-line-number" data-line-number="32"></td>
        <td id="LC32" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L33" class="blob-num js-line-number" data-line-number="33"></td>
        <td id="LC33" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L34" class="blob-num js-line-number" data-line-number="34"></td>
        <td id="LC34" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L35" class="blob-num js-line-number" data-line-number="35"></td>
        <td id="LC35" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L36" class="blob-num js-line-number" data-line-number="36"></td>
        <td id="LC36" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">getNumberOfStudentsShouldReturnTheCorrectNumberOfStudentsInTheDataStore</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L37" class="blob-num js-line-number" data-line-number="37"></td>
        <td id="LC37" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L38" class="blob-num js-line-number" data-line-number="38"></td>
        <td id="LC38" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L39" class="blob-num js-line-number" data-line-number="39"></td>
        <td id="LC39" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L40" class="blob-num js-line-number" data-line-number="40"></td>
        <td id="LC40" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">0</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L41" class="blob-num js-line-number" data-line-number="41"></td>
        <td id="LC41" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Athos<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L42" class="blob-num js-line-number" data-line-number="42"></td>
        <td id="LC42" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">1</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L43" class="blob-num js-line-number" data-line-number="43"></td>
        <td id="LC43" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Porthos<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L44" class="blob-num js-line-number" data-line-number="44"></td>
        <td id="LC44" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">2</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L45" class="blob-num js-line-number" data-line-number="45"></td>
        <td id="LC45" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Aramis<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L46" class="blob-num js-line-number" data-line-number="46"></td>
        <td id="LC46" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">3</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L47" class="blob-num js-line-number" data-line-number="47"></td>
        <td id="LC47" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>D&#39;Artagnan<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L48" class="blob-num js-line-number" data-line-number="48"></td>
        <td id="LC48" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">4</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L49" class="blob-num js-line-number" data-line-number="49"></td>
        <td id="LC49" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L50" class="blob-num js-line-number" data-line-number="50"></td>
        <td id="LC50" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L51" class="blob-num js-line-number" data-line-number="51"></td>
        <td id="LC51" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L52" class="blob-num js-line-number" data-line-number="52"></td>
        <td id="LC52" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L53" class="blob-num js-line-number" data-line-number="53"></td>
        <td id="LC53" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">theDataStoreShouldBeEmptyAfterAClear</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L54" class="blob-num js-line-number" data-line-number="54"></td>
        <td id="LC54" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L55" class="blob-num js-line-number" data-line-number="55"></td>
        <td id="LC55" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L56" class="blob-num js-line-number" data-line-number="56"></td>
        <td id="LC56" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L57" class="blob-num js-line-number" data-line-number="57"></td>
        <td id="LC57" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Gauvain<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L58" class="blob-num js-line-number" data-line-number="58"></td>
        <td id="LC58" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Lancelot<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L59" class="blob-num js-line-number" data-line-number="59"></td>
        <td id="LC59" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Arthur<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L60" class="blob-num js-line-number" data-line-number="60"></td>
        <td id="LC60" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Merlin<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L61" class="blob-num js-line-number" data-line-number="61"></td>
        <td id="LC61" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Perceval<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L62" class="blob-num js-line-number" data-line-number="62"></td>
        <td id="LC62" class="blob-code js-file-line">        client<span class="pl-k">.</span>clearDataStore();</td>
      </tr>
      <tr>
        <td id="L63" class="blob-num js-line-number" data-line-number="63"></td>
        <td id="LC63" class="blob-code js-file-line">        assertEquals(<span class="pl-c1">0</span>, client<span class="pl-k">.</span>getNumberOfStudents());</td>
      </tr>
      <tr>
        <td id="L64" class="blob-num js-line-number" data-line-number="64"></td>
        <td id="LC64" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L65" class="blob-num js-line-number" data-line-number="65"></td>
        <td id="LC65" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L66" class="blob-num js-line-number" data-line-number="66"></td>
        <td id="LC66" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L67" class="blob-num js-line-number" data-line-number="67"></td>
        <td id="LC67" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L68" class="blob-num js-line-number" data-line-number="68"></td>
        <td id="LC68" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">listStudentsShouldNeverReturnANullReference</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L69" class="blob-num js-line-number" data-line-number="69"></td>
        <td id="LC69" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L70" class="blob-num js-line-number" data-line-number="70"></td>
        <td id="LC70" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L71" class="blob-num js-line-number" data-line-number="71"></td>
        <td id="LC71" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L72" class="blob-num js-line-number" data-line-number="72"></td>
        <td id="LC72" class="blob-code js-file-line">        assertTrue(client<span class="pl-k">.</span>listStudents() <span class="pl-k">!=</span> <span class="pl-c1">null</span>);</td>
      </tr>
      <tr>
        <td id="L73" class="blob-num js-line-number" data-line-number="73"></td>
        <td id="LC73" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Personne<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L74" class="blob-num js-line-number" data-line-number="74"></td>
        <td id="LC74" class="blob-code js-file-line">        assertTrue(client<span class="pl-k">.</span>listStudents() <span class="pl-k">!=</span> <span class="pl-c1">null</span>);</td>
      </tr>
      <tr>
        <td id="L75" class="blob-num js-line-number" data-line-number="75"></td>
        <td id="LC75" class="blob-code js-file-line">        client<span class="pl-k">.</span>clearDataStore();</td>
      </tr>
      <tr>
        <td id="L76" class="blob-num js-line-number" data-line-number="76"></td>
        <td id="LC76" class="blob-code js-file-line">        assertTrue(client<span class="pl-k">.</span>listStudents() <span class="pl-k">!=</span> <span class="pl-c1">null</span>);</td>
      </tr>
      <tr>
        <td id="L77" class="blob-num js-line-number" data-line-number="77"></td>
        <td id="LC77" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L78" class="blob-num js-line-number" data-line-number="78"></td>
        <td id="LC78" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L79" class="blob-num js-line-number" data-line-number="79"></td>
        <td id="LC79" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L80" class="blob-num js-line-number" data-line-number="80"></td>
        <td id="LC80" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L81" class="blob-num js-line-number" data-line-number="81"></td>
        <td id="LC81" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">listStudentsShouldContainTheCorrectNumberOfStudents</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L82" class="blob-num js-line-number" data-line-number="82"></td>
        <td id="LC82" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L83" class="blob-num js-line-number" data-line-number="83"></td>
        <td id="LC83" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L84" class="blob-num js-line-number" data-line-number="84"></td>
        <td id="LC84" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L85" class="blob-num js-line-number" data-line-number="85"></td>
        <td id="LC85" class="blob-code js-file-line">        assertEquals(client<span class="pl-k">.</span>getNumberOfStudents(), client<span class="pl-k">.</span>listStudents()<span class="pl-k">.</span>size());</td>
      </tr>
      <tr>
        <td id="L86" class="blob-num js-line-number" data-line-number="86"></td>
        <td id="LC86" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Edward<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L87" class="blob-num js-line-number" data-line-number="87"></td>
        <td id="LC87" class="blob-code js-file-line">        assertEquals(client<span class="pl-k">.</span>getNumberOfStudents(), client<span class="pl-k">.</span>listStudents()<span class="pl-k">.</span>size());</td>
      </tr>
      <tr>
        <td id="L88" class="blob-num js-line-number" data-line-number="88"></td>
        <td id="LC88" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Bella<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L89" class="blob-num js-line-number" data-line-number="89"></td>
        <td id="LC89" class="blob-code js-file-line">        assertEquals(client<span class="pl-k">.</span>getNumberOfStudents(), client<span class="pl-k">.</span>listStudents()<span class="pl-k">.</span>size());</td>
      </tr>
      <tr>
        <td id="L90" class="blob-num js-line-number" data-line-number="90"></td>
        <td id="LC90" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Jacob<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L91" class="blob-num js-line-number" data-line-number="91"></td>
        <td id="LC91" class="blob-code js-file-line">        assertEquals(client<span class="pl-k">.</span>getNumberOfStudents(), client<span class="pl-k">.</span>listStudents()<span class="pl-k">.</span>size());</td>
      </tr>
      <tr>
        <td id="L92" class="blob-num js-line-number" data-line-number="92"></td>
        <td id="LC92" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L93" class="blob-num js-line-number" data-line-number="93"></td>
        <td id="LC93" class="blob-code js-file-line">    </td>
      </tr>
      <tr>
        <td id="L94" class="blob-num js-line-number" data-line-number="94"></td>
        <td id="LC94" class="blob-code js-file-line">    <span class="pl-k">@Test</span></td>
      </tr>
      <tr>
        <td id="L95" class="blob-num js-line-number" data-line-number="95"></td>
        <td id="LC95" class="blob-code js-file-line">    <span class="pl-k">@TestAuthor</span>(<span class="pl-c1">githubId</span> <span class="pl-k">=</span> {<span class="pl-s"><span class="pl-pds">&quot;</span>jurporan<span class="pl-pds">&quot;</span></span>, <span class="pl-s"><span class="pl-pds">&quot;</span>benoistwolleb<span class="pl-pds">&quot;</span></span>})</td>
      </tr>
      <tr>
        <td id="L96" class="blob-num js-line-number" data-line-number="96"></td>
        <td id="LC96" class="blob-code js-file-line">    <span class="pl-k">public</span> <span class="pl-k">void</span> <span class="pl-en">listStudentsShouldReturnAnEmptyListAfterAClear</span>() <span class="pl-k">throws</span> <span class="pl-smi">IOException</span></td>
      </tr>
      <tr>
        <td id="L97" class="blob-num js-line-number" data-line-number="97"></td>
        <td id="LC97" class="blob-code js-file-line">    {</td>
      </tr>
      <tr>
        <td id="L98" class="blob-num js-line-number" data-line-number="98"></td>
        <td id="LC98" class="blob-code js-file-line">        <span class="pl-smi">IRouletteV2Client</span> client <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">RouletteV2ClientImpl</span>();</td>
      </tr>
      <tr>
        <td id="L99" class="blob-num js-line-number" data-line-number="99"></td>
        <td id="LC99" class="blob-code js-file-line">        client<span class="pl-k">.</span>connect(<span class="pl-s"><span class="pl-pds">&quot;</span>localhost<span class="pl-pds">&quot;</span></span>, roulettePair<span class="pl-k">.</span>getServer()<span class="pl-k">.</span>getPort());</td>
      </tr>
      <tr>
        <td id="L100" class="blob-num js-line-number" data-line-number="100"></td>
        <td id="LC100" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Achille<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L101" class="blob-num js-line-number" data-line-number="101"></td>
        <td id="LC101" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Jason<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L102" class="blob-num js-line-number" data-line-number="102"></td>
        <td id="LC102" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Ajax<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L103" class="blob-num js-line-number" data-line-number="103"></td>
        <td id="LC103" class="blob-code js-file-line">        client<span class="pl-k">.</span>loadStudent(<span class="pl-s"><span class="pl-pds">&quot;</span>Bellerophon<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L104" class="blob-num js-line-number" data-line-number="104"></td>
        <td id="LC104" class="blob-code js-file-line">        client<span class="pl-k">.</span>clearDataStore();</td>
      </tr>
      <tr>
        <td id="L105" class="blob-num js-line-number" data-line-number="105"></td>
        <td id="LC105" class="blob-code js-file-line">        assertTrue(client<span class="pl-k">.</span>listStudents()<span class="pl-k">.</span>isEmpty());</td>
      </tr>
      <tr>
        <td id="L106" class="blob-num js-line-number" data-line-number="106"></td>
        <td id="LC106" class="blob-code js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L107" class="blob-num js-line-number" data-line-number="107"></td>
        <td id="LC107" class="blob-code js-file-line">}</td>
      </tr>
</table>

  </div>

</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <form accept-charset="UTF-8" action="" class="js-jump-to-line-form" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" autofocus>
    <button type="submit" class="btn">Go</button>
</form></div>

        </div>

      </div><!-- /.repo-container -->
      <div class="modal-backdrop"></div>
    </div><!-- /.container -->
  </div><!-- /.site -->


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer" role="contentinfo">
    <ul class="site-footer-links right">
        <li><a href="https://status.github.com/" data-ga-click="Footer, go to status, text:status">Status</a></li>
      <li><a href="https://developer.github.com" data-ga-click="Footer, go to api, text:api">API</a></li>
      <li><a href="https://training.github.com" data-ga-click="Footer, go to training, text:training">Training</a></li>
      <li><a href="https://shop.github.com" data-ga-click="Footer, go to shop, text:shop">Shop</a></li>
        <li><a href="https://github.com/blog" data-ga-click="Footer, go to blog, text:blog">Blog</a></li>
        <li><a href="https://github.com/about" data-ga-click="Footer, go to about, text:about">About</a></li>

    </ul>

    <a href="https://github.com" aria-label="Homepage">
      <span class="mega-octicon octicon-mark-github" title="GitHub"></span>
</a>
    <ul class="site-footer-links">
      <li>&copy; 2015 <span title="0.05979s from github-fe141-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="https://github.com/site/terms" data-ga-click="Footer, go to terms, text:terms">Terms</a></li>
        <li><a href="https://github.com/site/privacy" data-ga-click="Footer, go to privacy, text:privacy">Privacy</a></li>
        <li><a href="https://github.com/security" data-ga-click="Footer, go to security, text:security">Security</a></li>
        <li><a href="https://github.com/contact" data-ga-click="Footer, go to contact, text:contact">Contact</a></li>
    </ul>
  </div>
</div>


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-suggester-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="fullscreen-contents js-fullscreen-contents" placeholder=""></textarea>
      <div class="suggester-container">
        <div class="suggester fullscreen-suggester js-suggester js-navigation-container"></div>
      </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped tooltipped-w" aria-label="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped tooltipped-w"
      aria-label="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    
    

    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-x flash-close js-ajax-error-dismiss" aria-label="Dismiss error"></a>
      Something went wrong with that request. Please try again.
    </div>


      <script crossorigin="anonymous" src="https://assets-cdn.github.com/assets/frameworks-d22b59d0085e83b7549ba4341ec9e68f80c2f29c8e49213ee182003dc8d568c6.js"></script>
      <script async="async" crossorigin="anonymous" src="https://assets-cdn.github.com/assets/github-8480cce6cd52afd9c0e9035a3ac4640c9908195795d73afce21e2ea639a646bc.js"></script>
      
      

  </body>
</html>

